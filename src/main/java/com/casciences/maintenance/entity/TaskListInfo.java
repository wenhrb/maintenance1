package com.casciences.maintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (TaskListInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:05:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListInfo implements Serializable {
    private static final long serialVersionUID = 186545682905203800L;
    /**
     * 子任务id
     */
    private Integer taskId;
    /**
     * 工单Id
     */
    private Integer workId;
    /**
     * 任务状态（0：未完成，1：跳过，2 已完成）
     */
    private Integer taskStatus;
    /**
     * 维修事件（maintenance）id
     */
    private Integer matterId;
    /**
     * 任务执行人
     */
    private Integer workerInfoId;

    public TaskListInfo(Integer workId, int taskStatus, Integer matterId) {
        this.workId = workId;
        this.taskStatus = taskStatus;
        this.matterId = matterId;
    }
}