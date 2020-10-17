package com.casciences.maintenance.service.file;

import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.excelFile.ExcelFile;

import java.io.ByteArrayOutputStream;

/**
 * 文件下载服务
 *
 * @author lijie
 * @date 2020-09-13 22:25
 */
public interface FileDownLoadService {
    /**
     * 根据json数据导出文件
     *
     * @param excelFile  excel文件
     * @param jsonObject 数据集
     * @return
     */
    ByteArrayOutputStream exportFile(ExcelFile excelFile, JSONObject jsonObject);

}
