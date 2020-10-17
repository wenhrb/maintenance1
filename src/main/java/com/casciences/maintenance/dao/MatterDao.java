package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.Matter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Matter)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:05:41
 */
public interface MatterDao {

    /**
     * 通过ID查询单条数据
     *
     * @param matterId 主键
     * @return 实例对象
     */
    Matter queryById(Integer matterId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Matter> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param matter 实例对象
     * @return 对象列表
     */
    List<Matter> queryAll(Matter matter);

    /**
     * 新增数据
     *
     * @param matter 实例对象
     * @return 影响行数
     */
    int insert(Matter matter);

    /**
     * 修改数据
     *
     * @param matter 实例对象
     * @return 影响行数
     */
    int update(Matter matter);

    /**
     * 通过主键删除数据
     *
     * @param matterId 主键
     * @return 影响行数
     */
    int deleteById(Integer matterId);

    /**
     *
     * @param triggerIds
     * @return
     */
    List<Matter> queryMatterByTrigger(List<Integer> triggerIds);

}