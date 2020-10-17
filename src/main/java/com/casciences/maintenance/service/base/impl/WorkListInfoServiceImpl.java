package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.WorkListInfoDao;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.entity.WorkListInfo;
import com.casciences.maintenance.enums.WorkStateEnum;
import com.casciences.maintenance.service.base.TaskListInfoService;
import com.casciences.maintenance.service.base.WorkListInfoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
            int id = workListInfoDao.insert(workListInfo);
            workListInfo.setWorkId(id);
            List<TaskListInfo> taskListInfos = Lists.newLinkedList();
            for (Matter matter : workerMatter.getValue()) {
                TaskListInfo taskListInfo = new TaskListInfo(workListInfo.getWorkId(),WorkStateEnum.DEFAULT.getValue(), matter.getMatterId());
                taskListInfos.add(taskListInfo);
            }
            taskListInfoService.addTaskListInfos(taskListInfos);


        }
    }

    @Override
    public List<WorkListInfo> queryByUserAndState(Integer workerId, List<Integer> states) {
        return workListInfoDao.queryByUserAndState(workerId,states);
    }

    /**
     *
     * @param work 工单Id
     * @param confirmTaskIds 确认任务Id
     * @param skipTaskIds 跳过任务Id
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void confirmWorkListInfo(int work,List<Integer> confirmTaskIds,List<Integer> skipTaskIds) throws Exception{
        WorkListInfo workListInfo = workListInfoDao.queryById(work);
        if(workListInfo == null){
            throw new IllegalArgumentException("工单不合法");
        }
        List<TaskListInfo> taskListInfos = taskListInfoService.queryByWorkId(work);
        if(CollectionUtils.isEmpty(taskListInfos)){
            throw new IllegalArgumentException("工单不合法");
        }
        List<Integer>requestTaskList = Lists.newArrayList();
        requestTaskList.addAll(confirmTaskIds);
        requestTaskList.addAll(skipTaskIds);
        List<Integer> dbTaskList = taskListInfos.stream().map(TaskListInfo::getTaskId).collect(Collectors.toList());
        dbTaskList.removeAll(requestTaskList);
        if(CollectionUtils.isEmpty(dbTaskList)){
            workListInfo.setWorkStatus(WorkStateEnum.CONFIRM.getValue());
            workListInfoDao.update(workListInfo);
            taskListInfoService.batchUpdateState(skipTaskIds,WorkStateEnum.SKIP.getValue());
            taskListInfoService.batchUpdateState(confirmTaskIds,WorkStateEnum.CONFIRM.getValue());
        }else {
            throw new IllegalArgumentException("任务项错误，请确认是否全部处理");
        }
    }

}