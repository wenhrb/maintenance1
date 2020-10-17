package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.MatterTriggerDao;
import com.casciences.maintenance.entity.MatterTrigger;
import com.casciences.maintenance.enums.TriggerTypeEnums;
import com.casciences.maintenance.service.base.MatterTriggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (MatterTrigger)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:06:04
 */
@Service("triggerService")
public class MatterTriggerServiceImpl implements MatterTriggerService {
    @Resource
    private MatterTriggerDao matterTriggerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param triggerId 主键
     * @return 实例对象
     */
    @Override
    public MatterTrigger queryById(Integer triggerId) {
        return matterTriggerDao.queryById(triggerId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<MatterTrigger> queryAllByLimit(int offset, int limit) {
        return matterTriggerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param matterTrigger 实例对象
     * @return 实例对象
     */
    @Override
    public MatterTrigger insert(MatterTrigger matterTrigger) {
        matterTriggerDao.insert(matterTrigger);
        return matterTrigger;
    }

    /**
     * 修改数据
     *
     * @param matterTrigger 实例对象
     * @return 实例对象
     */
    @Override
    public MatterTrigger update(MatterTrigger matterTrigger) {
        matterTriggerDao.update(matterTrigger);
        return queryById(matterTrigger.getTriggerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param triggerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer triggerId) {
        return matterTriggerDao.deleteById(triggerId) > 0;
    }

    @Override
    public List<MatterTrigger> queryTimeTriggerByType(TriggerTypeEnums triggerTypeEnums) {
        return matterTriggerDao.queryTimeTriggerByType(triggerTypeEnums.getValue());
    }
}