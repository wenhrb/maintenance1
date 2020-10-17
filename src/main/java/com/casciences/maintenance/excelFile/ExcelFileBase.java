package com.casciences.maintenance.excelFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lijie7
 * @date 2018/4/13
 * @Description
 * @modified By
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFileBase {
    /**
     * 文件类型 指定接口类型 获取 配置文件的 对应的 配置信息
     */
    protected int type;
    /**
     * 文件名
     */
    protected String name;
    protected List<ExcelSheet> sheets;
}
