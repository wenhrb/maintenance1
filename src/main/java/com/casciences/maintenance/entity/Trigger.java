package com.casciences.maintenance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Trigger)实体类
 *
 * @author makejava
 * @since 2020-09-13 22:06:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trigger implements Serializable {
    private static final long serialVersionUID = 762799638543713060L;

    private Integer triggerId;

    private String description;
    private int type;

    private int timeSpan;

    private int timePoint;

}