package com.casciences.maintenance.excelFile;

import com.casciences.maintenance.enums.WorkerType;
import com.casciences.maintenance.util.ExcelConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author lijie7
 * @date 2018/5/23
 * @Description 方便使用  可以不加
 * @modified By
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FileType {


    /**
     * 声明一种文件
     */
    matter("matter", "demo1");
    /**
     * 文件标识
     */
    private String type;
    /**
     * 文件标识描述
     */
    private String desc;

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
