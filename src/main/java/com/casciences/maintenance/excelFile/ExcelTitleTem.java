package com.casciences.maintenance.excelFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lijie7
 * @date 2018/4/13
 * @Description
 * @modified By
 * 导出或者导入模板的表头信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelTitleTem {
    /**
     * 文件标题字段
     */
    private String titleName;
    /**
     * json -> excel 时对应的json的key值
     */
    private String dateName;
    /**
     * excel -> json 时 对应的json字段
     */
    private String imDateName;
}
