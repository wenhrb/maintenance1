package com.casciences.maintenance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * (EquipInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:04:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel
public class EquipInfo implements Serializable {
    private static final long serialVersionUID = -31370450496140860L;
    /**
     * 装备Id
     */
    @ApiModelProperty(example = "0")
    private Integer equipId;
    /**
     * 装备名称
     */
    private String equipName;
    /**
     * 配件名称
     */
    private String partName;
    /**
     * 配件类型（0：装备 ， 1：配件）
     */
    private Boolean partType;
    /**
     * 阈值
     */
    private String state;

    /**
     * @see com.casciences.maintenance.enums.WorkerType
     */
    @ApiModelProperty(example = "11")
    private Integer workerType;


    public EquipInfo(String equipName, String partName, Boolean partType, String state) {
        this.equipName = equipName;
        this.partName = partName;
        this.partType = partType;
        this.state = state;
    }
}