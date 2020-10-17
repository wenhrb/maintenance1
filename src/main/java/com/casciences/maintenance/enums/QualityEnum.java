package com.casciences.maintenance.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijie
 * @date 2020-10-17 17:13
 */
@Getter
@AllArgsConstructor
@ToString
public enum QualityEnum {
    ONE(1, "较差"),
    TWO(2, "差"),
    THREE(3, "一般"),
    FOUR(4, "好"),
    FIVE(5, "非常好");
    int value;
    String desc;

    public static QualityEnum getValue(int value) {
        for (QualityEnum qualityEnum : QualityEnum.values()) {
            if (value == qualityEnum.getValue()) {
                return qualityEnum;
            }
        }
        return null;
    }

    /**
     * 获取说明
     *
     * @return
     */
    public static Map<Integer, String> getDescMessage() {
        Map<Integer, String> map = new HashMap<>();
        for (QualityEnum value : QualityEnum.values()) {
            map.put(value.getValue(), value.getDesc());
        }
        return map;
    }
}
