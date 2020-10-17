package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.WorkerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WorkerInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:06:29
 */
public interface WorkerInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workerId 主键
     * @return 实例对象
     */
    WorkerInfo queryById(Integer workerId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkerInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param workerInfo 实例对象
     * @return 对象列表
     */
    List<WorkerInfo> queryAll(WorkerInfo workerInfo);

    /**
     * 新增数据
     *
     * @param workerInfo 实例对象
     * @return 影响行数
     */
    int insert(WorkerInfo workerInfo);

    /**
     * 修改数据
     *
     * @param workerInfo 实例对象
     * @return 影响行数
     */
    int update(WorkerInfo workerInfo);

    /**
     * 通过主键删除数据
     *
     * @param workerId 主键
     * @return 影响行数
     */
    int deleteById(Integer workerId);

}