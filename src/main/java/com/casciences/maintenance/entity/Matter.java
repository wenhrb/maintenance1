package com.casciences.maintenance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Matter)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:05:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Matter implements Serializable {
    private static final long serialVersionUID = -38061296652521712L;
    /**
     * 事件Id
     */
    private Integer matterId;
    /**
     * 装备Id
     */
    private Integer equipId;
    /**
     * 配件Id
     */
    private Integer partId;
    /**
     * 前导动作
     */
    private String preOp;
    /**
     * 标准/阈值
     */
    private String execuStandard;
    /**
     * 保养动作
     */
    private String maintOp;
    /**
     * 工作时间
     */
    private Integer matterTriggerId;

    /**
     * @see com.casciences.maintenance.enums.WorkerType
     */
    private Integer workerType;

}