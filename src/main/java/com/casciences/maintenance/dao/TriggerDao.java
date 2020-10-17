package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.Trigger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Trigger)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:06:04
 */
public interface TriggerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param triggerId 主键
     * @return 实例对象
     */
    Trigger queryById(Integer triggerId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Trigger> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param trigger 实例对象
     * @return 对象列表
     */
    List<Trigger> queryAll(Trigger trigger);

    /**
     * 新增数据
     *
     * @param trigger 实例对象
     * @return 影响行数
     */
    int insert(Trigger trigger);

    /**
     * 修改数据
     *
     * @param trigger 实例对象
     * @return 影响行数
     */
    int update(Trigger trigger);

    /**
     * 通过主键删除数据
     *
     * @param triggerId 主键
     * @return 影响行数
     */
    int deleteById(Integer triggerId);

    /**
     *
     * @return
     */
    List<Trigger> queryTimeTriggerByType(int type);


}