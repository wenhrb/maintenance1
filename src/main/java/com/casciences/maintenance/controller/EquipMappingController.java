package com.casciences.maintenance.controller;

import com.casciences.maintenance.entity.EquipMapping;
import com.casciences.maintenance.service.base.EquipMappingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (EquipMapping)表控制层
 *
 * @author makejava
 * @since 2020-09-13 22:05:15
 */
@RestController
@RequestMapping("equipMapping")
public class EquipMappingController {
    /**
     * 服务对象
     */
    @Resource
    private EquipMappingService equipMappingService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public EquipMapping selectOne(Integer id) {
        return this.equipMappingService.queryById(id);
    }

}