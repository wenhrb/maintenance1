package com.casciences.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijie
 * @date 2020-09-26 16:51
 */
@AllArgsConstructor
@Getter
public enum  WorkerType {
    NORMAL(0,"普通维修工种"),
    ELECTRICITY(1,"电工工种");
    private int value;
    private String desce;
}
