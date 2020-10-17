package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.WorkListInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (WorkListInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:06:17
 */
public interface WorkListInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param workId 主键
     * @return 实例对象
     */
    WorkListInfo queryById(Integer workId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<WorkListInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param workListInfo 实例对象
     * @return 对象列表
     */
    List<WorkListInfo> queryAll(WorkListInfo workListInfo);

    /**
     * 新增数据
     *
     * @param workListInfo 实例对象
     * @return 影响行数
     */
    int insert(WorkListInfo workListInfo);

    /**
     * 修改数据
     *
     * @param workListInfo 实例对象
     * @return 影响行数
     */
    int update(WorkListInfo workListInfo);

    /**
     * 通过主键删除数据
     *
     * @param workId 主键
     * @return 影响行数
     */
    int deleteById(Integer workId);

    /**
     * 通过用户查找所有的工单
     *
     * @param workerId
     * @return
     */
    List<WorkListInfo> queryByUserAndState(@Param("worker") Integer workerId, @Param("workStates") List<Integer> states);


}