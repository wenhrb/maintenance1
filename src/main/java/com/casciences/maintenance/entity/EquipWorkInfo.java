package com.casciences.maintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (EquipWorkInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:05:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipWorkInfo implements Serializable {
    private static final long serialVersionUID = -31057538524202215L;
    /**
     * 本身Id
     */
    private Integer equipWorkId;
    /**
     * 装备启动时间
     */
    private Date equipStartTime;
    /**
     * 上次关闭时间
     */
    private Date equipLastShutdownTime;
    /**
     * 装备运行总时长
     */
    private Integer equipWorkHours;

}