package com.casciences.maintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (EquipMapping)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:05:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipMapping implements Serializable {
    private static final long serialVersionUID = -84676545340018233L;
    /**
     * 装映射备Id
     */
    private Integer equipMappingId;
    /**
     * 装备Id
     */
    private Integer equipId;
    /**
     * 配件Id
     */
    private Integer partId;
}