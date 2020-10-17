package com.casciences.maintenance.service.base;

import com.alibaba.fastjson.JSONArray;
import com.casciences.maintenance.entity.Matter;

import java.util.List;

/**
 * (Matter)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:05:41
 */
public interface MatterService {

    /**
     * 通过ID查询单条数据
     *
     * @param matterId 主键
     * @return 实例对象
     */
    Matter queryById(Integer matterId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Matter> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param matter 实例对象
     * @return 实例对象
     */
    Matter insert(Matter matter);

    /**
     * 修改数据
     *
     * @param matter 实例对象
     * @return 实例对象
     */
    Matter update(Matter matter) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param matterId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer matterId);

    /**
     * 通过主键删除数据
     *
     * @param matterIds 主键
     * @return 是否成功
     */
    boolean deleteByIds(List<Integer> matterIds) throws Exception;


    /**
     * 保存数据到db中
     *
     * @param jsonArray
     */
    void saveDataToDd(JSONArray jsonArray);

    /**
     * 通过triggerId 查询所有的事件
     *
     * @param triggerIds 定时任务的id
     * @return 对象列表
     */
    List<Matter> queryMatterByTrigger(List<Integer> triggerIds);

}