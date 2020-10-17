package com.casciences.maintenance.service.base;

import com.casciences.maintenance.entity.EquipWorkInfo;

import java.util.List;

/**
 * (EquipWorkInfo)表服务接口
 *
 * @author makejava
 * @since 2020-09-13 22:05:31
 */
public interface EquipWorkInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param equipWorkId 主键
     * @return 实例对象
     */
    EquipWorkInfo queryById(Integer equipWorkId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EquipWorkInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param equipWorkInfo 实例对象
     * @return 实例对象
     */
    EquipWorkInfo insert(EquipWorkInfo equipWorkInfo);

    /**
     * 修改数据
     *
     * @param equipWorkInfo 实例对象
     * @return 实例对象
     */
    EquipWorkInfo update(EquipWorkInfo equipWorkInfo);

    /**
     * 通过主键删除数据
     *
     * @param equipWorkId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer equipWorkId);

}