package com.casciences.maintenance.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lijie
 * @date 2020-09-26 17:37
 */
@Data
public class BackMessage<T> {
    private int code;
    private String msg;
    private T data;

    public static<T>  String successMessage(T data) {
        BackMessage backMessage = new BackMessage();
        backMessage.setCode(0);
        backMessage.setMsg("success");
        backMessage.setData(data);
        return JSON.toJSONString(backMessage);
    }

    public static<T> String errorMessage(T data) {
        BackMessage backMessage = new BackMessage();
        backMessage.setCode(-1);
        backMessage.setMsg("error");
        backMessage.setData(data);
        return JSON.toJSONString(backMessage);
    }
}
