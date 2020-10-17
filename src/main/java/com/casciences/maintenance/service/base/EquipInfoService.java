package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.enums.EquipType;

import java.util.List;

/**
 * (EquipInfo)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:04:36
 */
public interface EquipInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param equipId 主键
     * @return 实例对象
     */
    EquipInfo queryById(Integer equipId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EquipInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    EquipInfo insert(EquipInfo equipInfo);

    /**
     * 修改数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    EquipInfo update(EquipInfo equipInfo);

    /**
     * 通过主键删除数据
     *
     * @param equipId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer equipId);

    /**
     * 通过名称查找配件或者装备信息
     * @param name  配件或者装备名称
     * @param type  配及类型
     * @see com.casciences.maintenance.enums.EquipType
     * @return
     */
    EquipInfo queryEquipOrPartByName(String name, EquipType type);
}