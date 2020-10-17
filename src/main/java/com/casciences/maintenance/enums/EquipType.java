package com.casciences.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijie
 * @date 2020-09-23 01:00
 */
@Getter
@AllArgsConstructor
public enum EquipType {
    //装备
    EQUIP(false, "装备"),
    // 配件
    PART(true, "配件");
    /**
     * 类型
     */
    private Boolean type;
    /**
     * 说明
     */
    private String des;
}
