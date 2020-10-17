package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.Trigger;
import com.casciences.maintenance.enums.TriggerTypeEnums;

import java.util.List;

/**
 * (Trigger)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:06:04
 */
public interface TriggerService {

    /**
     * 通过ID查询单条数据
     *
     * @param triggerId 主键
     * @return 实例对象
     */
    Trigger queryById(Integer triggerId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Trigger> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param trigger 实例对象
     * @return 实例对象
     */
    Trigger insert(Trigger trigger);

    /**
     * 修改数据
     *
     * @param trigger 实例对象
     * @return 实例对象
     */
    Trigger update(Trigger trigger);

    /**
     * 通过主键删除数据
     *
     * @param triggerId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer triggerId);

    /**
     * 查询时间间隔的所有触发条件
     * @param triggerType 触发条件
     * @return 触发条件
     */
    List<Trigger> queryTimeTriggerByType(TriggerTypeEnums triggerType);

}