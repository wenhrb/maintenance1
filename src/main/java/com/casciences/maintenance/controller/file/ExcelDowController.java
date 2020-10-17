package com.casciences.maintenance.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.casciences.maintenance.excelFile.ExcelFile;
import com.casciences.maintenance.service.file.FileDownLoadService;
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

/**
 * @author lijie7
 * @date 2018/5/25
 * @Description
 * @modified By
 */
@Controller
@RequestMapping("/download")
public class ExcelDowController {
    @Autowired
    private FileDownLoadService excelDowService;

    @RequestMapping(value = "/jsonToFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<byte[]> down(@RequestBody JSONObject jsonObject) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "dict.txt");
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentDispositionFormData("excel", System.currentTimeMillis() + ".xls");
        ExcelFile file = new ExcelFile(jsonObject.getString("type"));
        ByteArrayOutputStream byteArrayOutputStream = excelDowService.exportFile(file, jsonObject);
        try {
            File file1 = new File("key.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file1);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] body = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok().headers(headers).body(body);

    }
}
