package com.casciences.maintenance.controller.file;

import com.alibaba.fastjson.JSONArray;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.file.impl.ExcelFileUploadService;
import com.casciences.maintenance.util.BckMes;
import com.google.common.base.Strings;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

/**
 * @author lijie
 * @date 2020-09-13 23:08
 */
@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

    @Autowired
    private ExcelFileUploadService excelUploadService;

    @Autowired
    private MatterService matterService;

    /**
     * @param fileType 说明上传文档的type，对应的是 excelFile.properties 的 matter
     * @param file     要上传的文件
     * @param rowNum   第几行开始是数据
     * @return
     */
    @RequestMapping("/fileToJson")
    @ResponseBody
    public String changeFile(@RequestParam String fileType, @RequestParam MultipartFile file, @RequestParam int rowNum) {
        Objects.requireNonNull(Strings.isNullOrEmpty(fileType) ? null : fileType, "文件类型不能为空");
        Objects.requireNonNull(file, "excel文件不能为空");
        rowNum = rowNum > 1 ? rowNum : 1;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            ExcelFile excelFile = new ExcelFile(fileType);
            JSONArray data = excelUploadService.parseFileToJsonArray(workbook, excelFile, rowNum);
            matterService.saveDataToDd(data);
            return BckMes.successMes(data);
        } catch (IOException e) {
            return BckMes.errorMes(e.toString());
        }

    }
}
