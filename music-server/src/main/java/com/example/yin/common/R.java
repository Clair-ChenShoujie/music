package com.example.yin.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 前后端交互响应结果
 **/
@Data

//仿照类似于jason类型的数据格式
public class R {

    private int code; //响应码

    private String message; //响应信息 描述字符串

    private String type;//

    private Boolean success;

    private Object data;   //用来存放具体的返回的数据

    public static R success(String message) {
        R r = new R();
        r.setCode(200);
        r.setMessage(message);
        r.setSuccess(true);
        r.setType("success");
        r.setData(null);
        return r;
    }

    public static R success(String message, Object data) {
        R r = success(message);
//        从后端将数据传给前端
        r.setData(data);
        return r;
    }

    public static R warning(String message) {
        R r = error(message);
        r.setType("warning");
        return r;
    }

    public static R error(String message) {
        R r = success(message);
        r.setSuccess(false);
        r.setType("error");
        return r;
    }

    public static R fatal(String message) {
        R r = error(message);
        r.setCode(500);
        return r;
    }
}
