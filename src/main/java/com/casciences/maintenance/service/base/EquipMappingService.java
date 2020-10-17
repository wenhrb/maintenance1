package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.EquipMapping;

import java.util.List;

/**
 * (EquipMapping)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:05:15
 */
public interface EquipMappingService {

    /**
     * 通过ID查询单条数据
     *
     * @param equipMappingId 主键
     * @return 实例对象
     */
    EquipMapping queryById(Integer equipMappingId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EquipMapping> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param equipMapping 实例对象
     * @return 实例对象
     */
    EquipMapping insert(EquipMapping equipMapping);

    /**
     * 修改数据
     *
     * @param equipMapping 实例对象
     * @return 实例对象
     */
    EquipMapping update(EquipMapping equipMapping);

    /**
     * 通过主键删除数据
     *
     * @param equipMappingId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer equipMappingId);

}