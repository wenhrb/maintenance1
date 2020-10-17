package com.casciences.maintenance.controller.file;

import com.alibaba.fastjson.JSONArray;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.excelFile.FileType;
import com.casciences.maintenance.model.BackMessage;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.file.impl.ExcelFileUploadService;
import com.casciences.maintenance.util.BckMes;
import com.casciences.maintenance.util.ExcelConfig;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(value = "FileUploadController", tags = "文件上传")
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
    @ApiOperation(value = "上传维修信息", notes = "工单评分")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "/fileToJson",method = RequestMethod.POST,produces = "application/x-www-form-urlencoded")
    @ResponseBody
    public String changeFile(@ApiParam(value = "说明上传文档的type") @RequestParam String fileType,
                             @ApiParam(value = "要上传的文件") @RequestParam MultipartFile file,
                             @ApiParam(value = "第几行开始是数据,默认为1") @RequestParam(required = false, defaultValue = "1") int rowNum) {
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


    @ApiOperation(value = "获取上传文件类型信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @GetMapping("/queryUploadFileType")
    @ResponseBody
    public String getUploadFileType() {
        return BackMessage.successMessage(ExcelConfig.getFileTypes());
    }
}
