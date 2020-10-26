package com.casciences.maintenance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class TaskListInfo implements Serializable {
    private static final long serialVersionUID = 186545682905203800L;
    /**
     * 子任务id
     */
    @ApiModelProperty
    private Integer taskId;
    /**
     * 工单Id
     */
    @ApiModelProperty
    private Integer workId;
    /**
     * 任务状态（0：未完成，1：跳过，2 已完成）
     */
    @ApiModelProperty
    private Integer taskStatus;
    /**
     * 维修事件（maintenance）id
     */
    @ApiModelProperty
    private Integer matterId;
    /**
     * 任务执行人
     */
    @ApiModelProperty
    private Integer workerInfoId;

    public TaskListInfo(Integer workId, int taskStatus, Integer matterId) {
        this.workId = workId;
        this.taskStatus = taskStatus;
        this.matterId = matterId;
    }
}