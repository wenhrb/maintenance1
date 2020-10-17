package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.TaskListInfo;

import java.util.List;

/**
 * (TaskListInfo)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:05:52
 */
public interface TaskListInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    TaskListInfo queryById(Integer taskId);

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    List<TaskListInfo> queryByWorkId(Integer workId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TaskListInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param taskListInfo 实例对象
     * @return 实例对象
     */
    TaskListInfo insert(TaskListInfo taskListInfo);

    /**
     * 修改数据
     *
     * @param taskListInfo 实例对象
     * @return 实例对象
     */
    TaskListInfo update(TaskListInfo taskListInfo);

    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer taskId);

    /**
     * 新增数据
     *
     * @param taskListInfos 工单具体事项
     * @return 实例对象
     */
    void addTaskListInfos(List<TaskListInfo> taskListInfos);

    /**
     * 批量更新任务项
     *
     * @param taskListIds
     * @param state
     */
    void batchUpdateState(List<Integer> taskListIds, int state);

}