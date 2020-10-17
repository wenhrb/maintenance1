package com.casciences.maintenance.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 任务状态（0：初始状态，1：工人确认，2：已完成，3：存在遗留项）
 *
 * @author lijie
 * @date 2020-09-26 19:20
 */
@AllArgsConstructor
@Getter
public enum WorkStateEnum {

    //0：初始状态
    DEFAULT(0),
    //1：工人确认
    CONFIRM(1),
    //2：已完成
    FINISH(2),
    //3：存在遗留项
    SKIP(3);


    /**
     * value
     */
    private int value;


    /**
     * 获取工人和管理员允许看到的工单状态
     * @return
     */
    public static List<Integer> getWorkAllowStates(){
        List<Integer> states =  Lists.newArrayList();
        states.add(DEFAULT.getValue());
        states.add(CONFIRM.getValue());
        states.add(FINISH.getValue());
        states.add(SKIP.getValue());
        return states;
    }
}
