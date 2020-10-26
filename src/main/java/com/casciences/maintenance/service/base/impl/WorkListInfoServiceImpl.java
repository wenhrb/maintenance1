package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.WorkListInfoDao;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.enums.QualityEnum;
import com.casciences.maintenance.enums.WorkStateEnum;
import com.casciences.maintenance.service.base.TaskListInfoService;
import com.casciences.maintenance.service.base.WorkListInfoService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (WorkListInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:06:17
 */
@Slf4j
@Service("workListInfoService")
public class WorkListInfoServiceImpl implements WorkListInfoService {
    @Resource
    private WorkListInfoDao workListInfoDao;

    @Resource
    private TaskListInfoService taskListInfoService;


    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    @Override
    public WorkListInfo queryById(Integer workId) {
        return this.workListInfoDao.queryById(workId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<WorkListInfo> queryAllByLimit(int offset, int limit) {
        return this.workListInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param workListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkListInfo insert(WorkListInfo workListInfo) {
        this.workListInfoDao.insert(workListInfo);
        return workListInfo;
    }

    /**
     * 修改数据
     *
     * @param workListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public WorkListInfo update(WorkListInfo workListInfo) {
        this.workListInfoDao.update(workListInfo);
        return this.queryById(workListInfo.getWorkId());
    }

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer workId) {
        return this.workListInfoDao.deleteById(workId) > 0;
    }

    @Override
    @Transactional(rollbackOn = {Exception.class})
    public void createWorkInfo(List<Matter> matters) {
        if (CollectionUtils.isEmpty(matters)) {
            return;
        }
        Map<Integer, List<Matter>> workerMatterMap = matters.stream().collect(Collectors.groupingBy(Matter::getWorkerType));
        for (Map.Entry<Integer, List<Matter>> workerMatter : workerMatterMap.entrySet()) {
            WorkListInfo workListInfo = new WorkListInfo();
            workListInfo.setWorkerType(workerMatter.getKey());
            workListInfoDao.insert(workListInfo);
            workListInfo.setWorkId(workListInfo.getWorkId());
            List<TaskListInfo> taskListInfos = Lists.newLinkedList();
            for (Matter matter : workerMatter.getValue()) {
                TaskListInfo taskListInfo = new TaskListInfo(workListInfo.getWorkId(), WorkStateEnum.DEFAULT.getValue(), matter.getMatterId());
                taskListInfos.add(taskListInfo);
            }
            taskListInfoService.addTaskListInfos(taskListInfos);
            log.debug("自动创建工单：" + workListInfo.getWorkId());
        }
        log.info("自动创建工单完成，总计：{}", workerMatterMap.keySet().size());
    }

    @Override
    public List<WorkListInfo> queryByUserAndState(Integer workerId, List<Integer> states) {
        return workListInfoDao.queryByUserAndState(workerId, states);
    }

    /**
     * @param work           工单Id
     * @param confirmTaskIds 确认任务Id
     * @param skipTaskIds    跳过任务Id
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void confirmWorkListInfo(int work, List<Integer> confirmTaskIds, List<Integer> skipTaskIds) throws Exception {
        WorkListInfo workListInfo = workListInfoDao.queryById(work);
        if (workListInfo == null) {
            throw new IllegalArgumentException("工单不合法");
        }
        List<TaskListInfo> taskListInfos = taskListInfoService.queryByWorkId(work);
        if (CollectionUtils.isEmpty(taskListInfos)) {
            throw new IllegalArgumentException("工单不合法");
        }
        List<Integer> requestTaskList = Lists.newArrayList();
        requestTaskList.addAll(confirmTaskIds);
        requestTaskList.addAll(skipTaskIds);
        List<Integer> dbTaskList = taskListInfos.stream().map(TaskListInfo::getTaskId).collect(Collectors.toList());
        dbTaskList.removeAll(requestTaskList);
        if (CollectionUtils.isEmpty(dbTaskList)) {
            workListInfo.setWorkStatus(WorkStateEnum.CONFIRM.getValue());
            if (CollectionUtils.isEmpty(skipTaskIds)) {
                //存在跳过的任务项，重新生成新的工单（遗留项工单）
                createSkipWorkList(workListInfo, skipTaskIds);
            }
            TaskListInfo taskListInfo = new TaskListInfo();
            taskListInfo.setTaskStatus(WorkStateEnum.CONFIRM.getValue());
            taskListInfoService.batchUpdateTaskList(confirmTaskIds, taskListInfo);
            workListInfoDao.update(workListInfo);
        } else {
            throw new IllegalArgumentException("任务项错误，请确认是否全部处理");
        }
    }


    @Override
    public void ratingQuality(int workListId, int quality) throws Exception {
        WorkListInfo workListInfo = workListInfoDao.queryById(workListId);
        if (workListInfo == null) {
            throw new Exception("工单不存在");
        }
        QualityEnum qualityEnum = QualityEnum.getValue(quality);
        if (qualityEnum == null) {
            throw new Exception("请做出正确评分," + StringUtils.arrayToCommaDelimitedString(QualityEnum.values()));
        }
        workListInfo.setWorkQuality(quality);
        workListInfoDao.update(workListInfo);
    }

    /**
     * 生成遗留项工单
     *
     * @param workListInfo 原来的工单
     * @param skipTaskIds  跳过的任务项
     */
    private void createSkipWorkList(WorkListInfo workListInfo, List<Integer> skipTaskIds) {
        WorkListInfo skipWork = new WorkListInfo();
        BeanUtils.copyProperties(workListInfo, skipWork);
        skipWork.setWorkStatus(WorkStateEnum.SKIP.getValue());
        skipWork.setWorkId(null);
        workListInfoDao.insert(skipWork);
        TaskListInfo taskListInfo = new TaskListInfo();
        taskListInfo.setWorkerInfoId(skipWork.getWorkId());
        taskListInfo.setTaskStatus(WorkStateEnum.DEFAULT.getValue());
        taskListInfoService.batchUpdateTaskList(skipTaskIds, taskListInfo);
    }
}