package com.casciences.maintenance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lijie
 * @date 2020-09-23 00:40
 */
@Data
@AllArgsConstructor
public class MatterExcel {
    @JsonProperty("equipId")
    private int equipId;
    @JsonProperty("equip")
    private String equip;
    @JsonProperty("part")
    private String part;
    @JsonProperty("matterTrigger")
    private String matterTrigger;
    @JsonProperty("preOp")
    private String preOp;
    @JsonProperty("state")
    private String state;
    @JsonProperty("workerType")
    private int workerType;
}
