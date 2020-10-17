package com.casciences.maintenance.excelFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lijie7
 * @date 2018/5/23
 * @Description 方便使用  可以不加
 * @modified By
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum FileType {


    /**
     * 声明一种文件
     */
    matter("matter", "demo1");
    /**
     * 文件标识
     */
    private String type;
    /**
     * 文件标识描述
     */
    private String desc;


}
