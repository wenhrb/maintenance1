package com.casciences.maintenance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class WorkListInfo implements Serializable {
    private static final long serialVersionUID = -11648838125505463L;
    /**
     * 任务Id
     */
    @ApiModelProperty
    private Integer workId;
    /**
     * 任务下达时间
     */
    @ApiModelProperty
    private Date workOrderTime;
    /**
     * 任务开始时间
     */
    @ApiModelProperty
    private Date workStartTime;
    /**
     * 任务执行人
     */
    @ApiModelProperty
    private Integer worker;
    /**
     * 任务状态（0：初始状态，1：工人确认，2：已完成，3：存在遗留项）
     */
    @ApiModelProperty
    private Integer workStatus;
    /**
     * 任务更新时间
     */
    @ApiModelProperty
    private Date workUpdateTime;
    /**
     * 任务持续时长
     */
    @ApiModelProperty
    private Integer workHours;
    /**
     * 任务评分
     */
    @ApiModelProperty
    private Integer workQuality;

    /**
     * 执行人工种
     */
    @ApiModelProperty
    private Integer workerType;

    /**
     * 备注
     */
    @ApiModelProperty
    private String remark;

    public WorkListInfo() {
        this.workOrderTime = new Date();
        this.workStatus = 0;
    }
}