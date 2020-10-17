package com.casciences.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijie
 * @date 2020-09-26 10:12
 */
@AllArgsConstructor
@Getter
public enum TriggerTypeEnums {

    TIME_SPAN(0, "时间间隔"),
    TIME_POINT(1, "时间点");

    private int value;

    private String desc;
}
