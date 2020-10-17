package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.EquipInfoDao;
import com.casciences.maintenance.entity.EquipInfo;
import com.casciences.maintenance.enums.EquipType;
import com.casciences.maintenance.service.base.EquipInfoService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EquipInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:04:39
 */
@Service("equipInfoService")
public class EquipInfoServiceImpl implements EquipInfoService {
    @Resource
    private EquipInfoDao equipInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param equipId 主键
     * @return 实例对象
     */
    @Override
    public EquipInfo queryById(Integer equipId) {
        return this.equipInfoDao.queryById(equipId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EquipInfo> queryAllByLimit(int offset, int limit) {
        return this.equipInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    @Override
    public EquipInfo insert(EquipInfo equipInfo) {
        this.equipInfoDao.insert(equipInfo);
        return equipInfo;
    }

    /**
     * 修改数据
     *
     * @param equipInfo 实例对象
     * @return 实例对象
     */
    @Override
    public EquipInfo update(EquipInfo equipInfo) {
        this.equipInfoDao.update(equipInfo);
        return this.queryById(equipInfo.getEquipId());
    }

    /**
     * 通过主键删除数据
     *
     * @param equipId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer equipId) {
        return this.equipInfoDao.deleteById(equipId) > 0;
    }

    @Override
    public EquipInfo queryEquipOrPartByName(String name, EquipType type) {
        List<EquipInfo> equipInfos = Lists.newArrayList();
        if (EquipType.EQUIP == type) {
            equipInfos = equipInfoDao.queryEquipByName(name);
        } else {
            equipInfos = equipInfoDao.queryPartByName(name);
        }
        return CollectionUtils.isEmpty(equipInfos) ? null : equipInfos.get(0);
    }


}