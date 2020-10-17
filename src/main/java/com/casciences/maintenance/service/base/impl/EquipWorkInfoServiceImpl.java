package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.EquipWorkInfoDao;
import com.casciences.maintenance.entity.EquipWorkInfo;
import com.casciences.maintenance.service.base.EquipWorkInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (EquipWorkInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:05:31
 */
@Service("equipWorkInfoService")
public class EquipWorkInfoServiceImpl implements EquipWorkInfoService {
    @Resource
    private EquipWorkInfoDao equipWorkInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param equipWorkId 主键
     * @return 实例对象
     */
    @Override
    public EquipWorkInfo queryById(Integer equipWorkId) {
        return equipWorkInfoDao.queryById(equipWorkId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EquipWorkInfo> queryAllByLimit(int offset, int limit) {
        return equipWorkInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param equipWorkInfo 实例对象
     * @return 实例对象
     */
    @Override
    public EquipWorkInfo insert(EquipWorkInfo equipWorkInfo) {
        equipWorkInfoDao.insert(equipWorkInfo);
        return equipWorkInfo;
    }

    /**
     * 修改数据
     *
     * @param equipWorkInfo 实例对象
     * @return 实例对象
     */
    @Override
    public EquipWorkInfo update(EquipWorkInfo equipWorkInfo) {
        equipWorkInfoDao.update(equipWorkInfo);
        return queryById(equipWorkInfo.getEquipWorkId());
    }

    /**
     * 通过主键删除数据
     *
     * @param equipWorkId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer equipWorkId) {
        return equipWorkInfoDao.deleteById(equipWorkId) > 0;
    }
}