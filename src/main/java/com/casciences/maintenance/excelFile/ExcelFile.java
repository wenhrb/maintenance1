package com.casciences.maintenance.excelFile;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.util.ExcelConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author lijie7
 * @date 2017/12/16
 * @Description
 * @modified By
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFile {

    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件描述
     */
    private ExcelDoc excelDoc;

    public ExcelFile(String type){
        this.type = type;
        this.excelDoc = formatExcelDoc(type);
    }

    private ExcelDoc formatExcelDoc(String type) {
        String jsonStr = ExcelConfig.getMe().get(type);
        ExcelDoc excelDoc = new ExcelDoc();
        Map<String, ExcelSheet> excelSheets = new HashMap<String, ExcelSheet>();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        if (jsonObject.containsKey("data") && jsonObject.containsKey("title")) {
            JSONArray titles = jsonObject.getJSONArray("title");
            JSONArray data = jsonObject.getJSONArray("data");
            JSONArray imData = jsonObject.getJSONArray("imData");
            JSONArray imDatas = (imData == null || imData.size() < 0) ? new JSONArray() : imData;
            List<ExcelTitleTem> titleTems = new ArrayList<ExcelTitleTem>();
            for (int i = 0; i < titles.size(); i++) {
                String imDataTemp = imDatas.size() > 0 ? imDatas.getString(i) : "";
                ExcelTitleTem excelTitleTem = new ExcelTitleTem(titles.getString(i), data.getString(i), imDataTemp);
                titleTems.add(excelTitleTem);
            }
            String sheetName = "sheet";
            ExcelSheet sheet = new ExcelSheet(sheetName, titleTems);
            excelSheets.put(sheetName, sheet);
        } else {
            Iterator<String> iterator = jsonObject.keySet().iterator();
            while (iterator.hasNext()) {
                String dataName = iterator.next();
                JSONObject temp = jsonObject.getJSONObject(dataName);
                String sheetName = temp.getString("sheetName");
                JSONArray titles = temp.getJSONArray("title");
                JSONArray data = temp.getJSONArray("data");
                JSONArray imData = temp.getJSONArray("imData");
                JSONArray imDatas = (imData == null || imData.size() < 0) ? new JSONArray() : imData;
                List<ExcelTitleTem> titleTems = new ArrayList<ExcelTitleTem>();
                for (int i = 0; i < titles.size(); i++) {
                    String imDataTemp = imDatas.size() > 0 ? imDatas.getString(i) : "";
                    ExcelTitleTem excelTitleTem = new ExcelTitleTem(titles.getString(i), data.getString(i), imDataTemp);
                    titleTems.add(excelTitleTem);
                }
                ExcelSheet sheet = new ExcelSheet(sheetName, titleTems);
                excelSheets.put(dataName, sheet);
            }
        }
        excelDoc.setExcelSheetMap(excelSheets);

        return excelDoc;
    }
}
