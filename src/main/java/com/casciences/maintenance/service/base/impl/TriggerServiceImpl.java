package com.casciences.maintenance.service.base.impl;

import com.casciences.maintenance.dao.TriggerDao;
import com.casciences.maintenance.entity.Trigger;
import com.casciences.maintenance.enums.TriggerTypeEnums;
import com.casciences.maintenance.service.base.TriggerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Trigger)表服务实现类
 *
 * @author makejava
 * @since 2020-09-13 22:06:04
 */
@Service("triggerService")
public class TriggerServiceImpl implements TriggerService {
    @Resource
    private TriggerDao triggerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param triggerId 主键
     * @return 实例对象
     */
    @Override
    public Trigger queryById(Integer triggerId) {
        return triggerDao.queryById(triggerId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Trigger> queryAllByLimit(int offset, int limit) {
        return triggerDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param trigger 实例对象
     * @return 实例对象
     */
    @Override
    public Trigger insert(Trigger trigger) {
        triggerDao.insert(trigger);
        return trigger;
    }

    /**
     * 修改数据
     *
     * @param trigger 实例对象
     * @return 实例对象
     */
    @Override
    public Trigger update(Trigger trigger) {
        triggerDao.update(trigger);
        return queryById(trigger.getTriggerId());
    }

    /**
     * 通过主键删除数据
     *
     * @param triggerId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer triggerId) {
        return triggerDao.deleteById(triggerId) > 0;
    }

    @Override
    public List<Trigger> queryTimeTriggerByType(TriggerTypeEnums triggerTypeEnums) {
        return triggerDao.queryTimeTriggerByType(triggerTypeEnums.getValue());
    }
}