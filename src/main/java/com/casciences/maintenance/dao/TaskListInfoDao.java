package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.TaskListInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TaskListInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:05:52
 */
public interface TaskListInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param taskId 主键
     * @return 实例对象
     */
    TaskListInfo queryById(Integer taskId);

    /**
     * @param workId
     * @return
     */
    List<TaskListInfo> queryByWorkId(Integer workId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TaskListInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param taskListInfo 实例对象
     * @return 对象列表
     */
    List<TaskListInfo> queryAll(TaskListInfo taskListInfo);

    /**
     * 新增数据
     *
     * @param taskListInfo 实例对象
     * @return 影响行数
     */
    int insert(TaskListInfo taskListInfo);

    /**
     * 修改数据
     *
     * @param taskListInfo 实例对象
     * @return 影响行数
     */
    int update(TaskListInfo taskListInfo);

    /**
     * 通过主键删除数据
     *
     * @param taskId 主键
     * @return 影响行数
     */
    int deleteById(Integer taskId);

    /**
     * 新增多个任务
     *
     * @param taskListInfos 任务列表
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