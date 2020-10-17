package com.casciences.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lijie
 * @date 2020-09-26 17:53
 */
@Getter
@AllArgsConstructor
public enum WorkerStateEnum {
    //上线
    ONLINE(1),
    //下线
    OFFLINE(0),
    //请假
    QINFJIA(2),
    //待工
    DAIGONG(3);
    /**
     * 状态描述信息
     */
    private int value;

    public static WorkerStateEnum getInstance(int value){
        for (int i = 0; i < values().length; i++) {
            if(values()[i].getValue() == value){
                return values()[i];
            }
        }
        return OFFLINE;
    }
}
