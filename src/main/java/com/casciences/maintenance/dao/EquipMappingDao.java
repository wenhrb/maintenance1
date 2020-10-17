package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.EquipMapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (EquipMapping)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:05:14
 */
public interface EquipMappingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param equipMappingId 主键
     * @return 实例对象
     */
    EquipMapping queryById(Integer equipMappingId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EquipMapping> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param equipMapping 实例对象
     * @return 对象列表
     */
    List<EquipMapping> queryAll(EquipMapping equipMapping);

    /**
     * 新增数据
     *
     * @param equipMapping 实例对象
     * @return 影响行数
     */
    int insert(EquipMapping equipMapping);

    /**
     * 修改数据
     *
     * @param equipMapping 实例对象
     * @return 影响行数
     */
    int update(EquipMapping equipMapping);

    /**
     * 通过主键删除数据
     *
     * @param equipMappingId 主键
     * @return 影响行数
     */
    int deleteById(Integer equipMappingId);

}