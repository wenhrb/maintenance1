package com.casciences.maintenance.service.file.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.excelFile.ExcelSheet;
import com.casciences.maintenance.service.file.FileDownLoadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Map;


/**
 * @author lijie7
 * @date 2018/5/25
 * @Description
 * @modified By
 */
@Service
@Slf4j
public class FileDownLoadServiceImpl extends AbstractExcelDown implements FileDownLoadService {


    /**
     * 文件数据导出
     *
     * @param excelFile
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public ByteArrayOutputStream exportFile(ExcelFile excelFile, JSONObject jsonObject) {
        ByteArrayOutputStream bos = null;
        try {
            if (!jsonObject.get("datas").getClass().getTypeName().equalsIgnoreCase("JSONObject")) {
                bos = getReportFileByJsonArray(excelFile, jsonObject.getJSONArray("datas"));
            } else {
                bos = getReportFileByJsonObject(excelFile, jsonObject.getJSONObject("datas"));
            }
        } catch (Exception e) {
            log.error("文件生成错误,e:{}", e.toString());
        }
        return bos;
    }

    /**
     * 报表导出
     *
     * @param jsonArray
     * @return
     * @throws Exception
     */
    private ByteArrayOutputStream getReportFileByJsonArray(ExcelFile excelFile, JSONArray jsonArray) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("index");
                JSONArray users = jsonObject.getJSONArray("users");
                ExcelSheet excelSheet = excelFile.getExcelDoc().getExcelSheetMap().get("sheet");
                excelSheet.setSheetName(name);
                workbook = createKeySheet(workbook, users, excelSheet);
            }
            workbook.write(bos);
        } catch (Exception e) {
            log.error("文件生成错误,e:{}", e.toString());
        }
        return bos;
    }

    /**
     * 报表导出
     *
     * @param jsonObject
     * @return
     * @throws Exception `根据json字段生成多个sheet
     */
    private ByteArrayOutputStream getReportFileByJsonObject(ExcelFile excelFile, JSONObject jsonObject) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Map<String, ExcelSheet> excelSheets = excelFile.getExcelDoc().getExcelSheetMap();
            Iterator<String> fields = jsonObject.keySet().iterator();
            while (fields.hasNext()) {
                String name = fields.next();
                JSONArray jsonArray = jsonObject.getJSONArray(name);
                ExcelSheet excelSheet = excelSheets.get(name);
                if (excelSheet != null) {
                    workbook = createDefaultSheet(workbook, jsonArray, excelSheet);
                }
            }
            workbook.write(bos);
        } catch (Exception e) {
            log.error("文件生成错误,e:{}", e.toString());
        }
        return bos;
    }


}
