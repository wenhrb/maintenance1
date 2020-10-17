package com.casciences.maintenance.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.entity.Matter;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.excelFile.FileType;
import com.casciences.maintenance.service.base.MatterService;
import com.casciences.maintenance.service.file.FileDownLoadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author lijie7
 * @date 2018/5/25
 * @Description
 * @modified By
 */
@Controller
@RequestMapping("/download")
public class ExcelDownController {
    @Autowired
    private FileDownLoadService excelDowService;

    @Autowired
    private MatterService matterService;

    @ApiOperation(value = "根据条件查询事件信息下载", notes = "传入对象")
    @ApiParam(name = "matterInfo", value = "{\"matterId\":\"1\",\"equipId\":\"1\",\"part_id\":\"1\",\"pre_op\":\"检查\",\"execu_standard\":\"破损\",\"maint_op\":\"维修\",\"matter_trigger_id\":\"1\"}", required = true)
    @ApiResponses({
            @ApiResponse(code = -1, message = "失败", responseContainer = "message"),
            @ApiResponse(code = 1, message = "成功", responseContainer = "message,data"),
    })
    @RequestMapping(value = "/jsonToFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> down(@RequestBody Matter matter) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "dict.txt");
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentDispositionFormData("excel", System.currentTimeMillis() + ".xls");
        try (ByteArrayOutputStream byteArrayOutputStream = matterService.findAllMatterExcel(matter)) {

            byte[] body = byteArrayOutputStream.toByteArray();
//            File file1 = new File("key.xlsx");
//            FileOutputStream fileOutputStream = new FileOutputStream(file1);
//            fileOutputStream.write(byteArrayOutputStream.toByteArray());
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            byteArrayOutputStream.close();
            return ResponseEntity.ok().headers(headers).body(body);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
