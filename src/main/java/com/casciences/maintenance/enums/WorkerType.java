package com.casciences.maintenance.enums;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @date 2020-09-26 16:51
 */
@AllArgsConstructor
@Getter
public enum WorkerType {

    NORMAL(11, "普通维修工种"),
    ELECTRICITY(12, "电工工种"),
    ADMIN(1, "管理员"),
    CON_ADMIN(2, "信息管理员");
    private int value;
    private String desce;

    /**
     * 获取说明
     *
     * @return
     */
    public static Map<Integer, String> getDescMessage() {
        Map<Integer, String> map = new HashMap<>();
        for (WorkerType value : WorkerType.values()) {
            map.put(value.getValue(), value.getDesce());
        }
        return map;
    }
}
