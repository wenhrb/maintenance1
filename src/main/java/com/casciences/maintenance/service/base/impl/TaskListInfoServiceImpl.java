package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.TaskListInfoDao;
import com.casciences.maintenance.entity.TaskListInfo;
import com.casciences.maintenance.service.base.TaskListInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TaskListInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:05:52
 */
@Service("taskListInfoService")
public class TaskListInfoServiceImpl implements TaskListInfoService {
    @Resource
    private TaskListInfoDao taskListInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    @Override
    public TaskListInfo queryById(Integer taskId) {
        return taskListInfoDao.queryById(taskId);
    }

    @Override
    public List<TaskListInfo> queryByWorkId(Integer workId) {
        return taskListInfoDao.queryByWorkId(workId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<TaskListInfo> queryAllByLimit(int offset, int limit) {
        return taskListInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param taskListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskListInfo insert(TaskListInfo taskListInfo) {
        taskListInfoDao.insert(taskListInfo);
        return taskListInfo;
    }

    /**
     * 修改数据
     *
     * @param taskListInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskListInfo update(TaskListInfo taskListInfo) {
        taskListInfoDao.update(taskListInfo);
        return queryById(taskListInfo.getTaskId());
    }

    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer taskId) {
        return taskListInfoDao.deleteById(taskId) > 0;
    }

    @Override
    public void addTaskListInfos(List<TaskListInfo> taskListInfos) {
        taskListInfoDao.addTaskListInfos(taskListInfos);

    }

    /**
     * @param taskListIds
     * @param state
     * @return
     */
    @Override
    public void batchUpdateState(List<Integer> taskListIds, int state) {
        taskListInfoDao.batchUpdateState(taskListIds, state);
    }
}