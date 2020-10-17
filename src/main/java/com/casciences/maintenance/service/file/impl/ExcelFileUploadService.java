package com.casciences.maintenance.service.file.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.excelFile.ExcelTitleTem;
import com.casciences.maintenance.util.ExcelFileUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelFileUploadService {
    /**
     * 单个Sheet解析
     * @param workbook
     * @param file
     * @param dataRowNum
     * @return
     */
    public JSONArray parseFileToJsonArray(XSSFWorkbook workbook, ExcelFile file, int dataRowNum){
        JSONArray jsonArray = new JSONArray();
        List<ExcelTitleTem> titleTems = file.getExcelDoc().getExcelSheetMap().get("sheet").getTitleTemList();
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        for (int i = dataRowNum; i < rowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            int colNum = row.getPhysicalNumberOfCells();
            JSONObject jsonObject = new JSONObject();
            for (int j = 0; j < colNum; j++) {
                XSSFCell cell = row.getCell(j);
                Object a = ExcelFileUtil.getCellValue(cell);
                jsonObject.put(titleTems.get(j).getImDateName(),a);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }




}
