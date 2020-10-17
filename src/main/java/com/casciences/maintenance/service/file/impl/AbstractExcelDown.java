package com.casciences.maintenance.service.file.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.excelFile.ExcelSheet;
import com.casciences.maintenance.excelFile.ExcelTitleTem;
import com.casciences.maintenance.util.ExcelFileUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

import static com.casciences.maintenance.util.ExcelFileUtil.getContentCellStyle;
import static com.casciences.maintenance.util.ExcelFileUtil.setCellFormatValue;

/**
 * @author lijie
 * @date 2020-09-13 22:34
 */
public abstract class AbstractExcelDown {
    /**
     * 默认调用方法
     *
     * @param workbook   excel文件
     * @param jsonArray  数据集
     * @param excelSheet sheet
     * @return
     */
    protected XSSFWorkbook createKeySheet(XSSFWorkbook workbook, JSONArray jsonArray, ExcelSheet excelSheet) throws Exception {
        XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
        List<ExcelTitleTem> titleTems = excelSheet.getTitleTemList();
        sheet.setDisplayGridlines(false);
        XSSFCellStyle headStyle = ExcelFileUtil.getTitleCellStyle(workbook.createCellStyle(), workbook.createFont(), (short) 54);
        XSSFCellStyle intStyle = getContentCellStyle(workbook.createCellStyle(), workbook.createFont(), (short) 0);
        XSSFCellStyle stringStyle = (XSSFCellStyle) intStyle.clone();
        XSSFCellStyle doubleStyle = (XSSFCellStyle) intStyle.clone();
        intStyle.setDataFormat((short) 3);
        doubleStyle.setDataFormat((short) 4);
        XSSFRow tableHead = sheet.createRow(0);
        XSSFRow tableHead1 = sheet.createRow(1);
        for (int i = 0; i < titleTems.size(); i++) {
            XSSFCell cell = tableHead.createCell(i);
            cell.setCellValue(titleTems.get(i).getTitleName());
            cell.setCellStyle(headStyle);
            tableHead1.createCell(i).setCellStyle(headStyle);
            //合并左侧头部单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = jsonArray.getJSONObject(i);
            XSSFRow row = sheet.createRow(i + 2);
            for (int k = 0; k < titleTems.size(); k++) {
                String fileName = titleTems.get(k).getDateName();
                XSSFCell cell = row.createCell(k);
                setCellFormatValue(cell, jsonData.get(fileName), stringStyle, intStyle, doubleStyle);
            }
        }
        ExcelFileUtil.setAutoColumnSize(workbook, workbook.getSheetIndex(sheet), 0);
        return workbook;
    }

    /**
     * 默认调用方法
     *
     * @param workbook   excel对象
     * @param jsonArray  数据集
     * @param excelSheet sheet
     * @return
     */
    protected XSSFWorkbook createDefaultSheet(XSSFWorkbook workbook, JSONArray jsonArray, ExcelSheet excelSheet) throws Exception {
        XSSFSheet sheet = workbook.createSheet(excelSheet.getSheetName());
        List<ExcelTitleTem> titleTems = excelSheet.getTitleTemList();
        sheet.setDisplayGridlines(false);
        XSSFCellStyle headStyle = ExcelFileUtil.getTitleCellStyle(workbook.createCellStyle(), workbook.createFont(), (short) 54);
        XSSFCellStyle intStyle = getContentCellStyle(workbook.createCellStyle(), workbook.createFont(), (short) 0);
        XSSFCellStyle stringStyle = (XSSFCellStyle) intStyle.clone();
        XSSFCellStyle doubleStyle = (XSSFCellStyle) intStyle.clone();
        intStyle.setDataFormat((short) 3);
        doubleStyle.setDataFormat((short) 4);
        XSSFRow tableHead = sheet.createRow(0);
        XSSFRow tableHead1 = sheet.createRow(1);
        for (int i = 0; i < titleTems.size(); i++) {
            XSSFCell cell = tableHead.createCell(i);
            cell.setCellValue(titleTems.get(i).getTitleName());
            cell.setCellStyle(headStyle);
            tableHead1.createCell(i).setCellStyle(headStyle);
            //合并左侧头部单元格
            sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = jsonArray.getJSONObject(i);
            XSSFRow row = sheet.createRow(i + 2);
            for (int k = 0; k < titleTems.size(); k++) {
                String fileName = titleTems.get(k).getDateName();
                XSSFCell cell = row.createCell(k);
                setCellFormatValue(cell, jsonData.get(fileName), stringStyle, intStyle, doubleStyle);
            }
        }
        ExcelFileUtil.setAutoColumnSize(workbook, workbook.getSheetIndex(sheet), 0);
        return workbook;
    }
}
