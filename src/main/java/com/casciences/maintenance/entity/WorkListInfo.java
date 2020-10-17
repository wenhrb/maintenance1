package com.casciences.maintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * (WorkListInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:06:17
 */
@Data
@AllArgsConstructor
@ToString
public class WorkListInfo implements Serializable {
    private static final long serialVersionUID = -11648838125505463L;
    /**
     * 任务Id
     */
    private Integer workId;
    /**
     * 任务下达时间
     */
    private Date workOrderTime;
    /**
     * 任务开始时间
     */
    private Date workStartTime;
    /**
     * 任务执行人
     */
    private Integer worker;
    /**
     * 任务状态（0：初始状态，1：工人确认，2：已完成，3：存在遗留项）
     */
    private Integer workStatus;
    /**
     * 任务更新时间
     */
    private Date workUpdateTime;
    /**
     * 任务持续时长
     */
    private Integer workHours;
    /**
     * 任务评分
     */
    private Integer workQuality;

    /**
     * 执行人工种
     */
    private Integer workerType;

    public WorkListInfo() {
        this.workOrderTime = new Date();
        this.workStatus = 0;
    }
}