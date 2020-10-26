package com.casciences.maintenance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (WorkerInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:06:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class WorkerInfo implements Serializable {
    private static final long serialVersionUID = 763665690428775601L;
    /**
     * 工人Id
     */
    @ApiModelProperty(value = "工人Id",example = "0")
    private Integer workerId;
    /**
     * 工人名字
     */
    @ApiModelProperty(value = "工人名字")
    private String workerName;
    /**
     * 工种类型（1：维修工，2：电工）
     */
    @ApiModelProperty(value = "工人类型", allowableValues = "0,1,2",example = "0")
    private Integer workerType;
    /**
     * 工作时长（小时）
     */
    @ApiModelProperty(value = "工作时长（小时）",example = "100")
    private Integer hours;
    /**
     * 工作状态（0：待工，1：在工，2：请假 ）
     */
    @ApiModelProperty(value = "工人类型（0：待工，1：在工，2：请假 ）", allowableValues = "0,1,2" ,example = "1")
    private Integer workerState;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}