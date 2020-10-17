package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.EquipMappingDao;
import com.casciences.maintenance.entity.EquipMapping;
import com.casciences.maintenance.service.base.EquipMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EquipMapping)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:05:15
 */
@Service("equipMappingService")
public class EquipMappingServiceImpl implements EquipMappingService {
    @Resource
    private EquipMappingDao equipMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param equipMappingId 主键
     * @return 实例对象
     */
    @Override
    public EquipMapping queryById(Integer equipMappingId) {
        return this.equipMappingDao.queryById(equipMappingId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EquipMapping> queryAllByLimit(int offset, int limit) {
        return this.equipMappingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param equipMapping 实例对象
     * @return 实例对象
     */
    @Override
    public EquipMapping insert(EquipMapping equipMapping) {
        this.equipMappingDao.insert(equipMapping);
        return equipMapping;
    }

    /**
     * 修改数据
     *
     * @param equipMapping 实例对象
     * @return 实例对象
     */
    @Override
    public EquipMapping update(EquipMapping equipMapping) {
        this.equipMappingDao.update(equipMapping);
        return this.queryById(equipMapping.getEquipMappingId());
    }

    /**
     * 通过主键删除数据
     *
     * @param equipMappingId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer equipMappingId) {
        return this.equipMappingDao.deleteById(equipMappingId) > 0;
    }
}