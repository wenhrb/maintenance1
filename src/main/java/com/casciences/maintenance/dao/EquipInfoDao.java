package com.casciences.maintenance.dao;

import com.casciences.maintenance.entity.EquipInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (EquipInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-13 22:04:34
 */
public interface EquipInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param equipId 主键
     * @return 实例对象
     */
    EquipInfo queryById(Integer equipId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EquipInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param equipInfo 实例对象
     * @return 对象列表
     */
    List<EquipInfo> queryAll(EquipInfo equipInfo);

    /**
     * 新增数据
     *
     * @param equipInfo 实例对象
     * @return 影响行数
     */
    int insert(EquipInfo equipInfo);

    /**
     * 修改数据
     *
     * @param equipInfo 实例对象
     * @return 影响行数
     */
    int update(EquipInfo equipInfo);

    /**
     * 通过主键删除数据
     *
     * @param equipId 主键
     * @return 影响行数
     */
    int deleteById(Integer equipId);

    /**
     * 查找装备数据
     *
     * @param equipName 装备名称
     * @return 数据库中存在的数据
     */
    List<EquipInfo> queryEquipByName(String equipName);

    /**
     * 查找配件数据
     *
     * @param partName 装备名称
     * @return 数据库中存在的数据
     */
    List<EquipInfo> queryPartByName(String partName);
}