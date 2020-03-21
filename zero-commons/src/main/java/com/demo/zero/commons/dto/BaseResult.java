package com.demo.zero.commons.dto;


import java.io.Serializable;

/**
 * 自定义返回结果
 * <p>Title: BaseResult</p>
 * <p>Description: </p>
 */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 20000;
    public static final int STATUS_FAIL = 50000;


    private int code;
    private String message;
    private Object data;

    public static BaseResult success() {
        return BaseResult.createResult(STATUS_SUCCESS, "成功", null);
    }

    public static BaseResult success(String message) {
        return BaseResult.createResult(STATUS_SUCCESS, message, null);
    }

    public static BaseResult success(String message, Object data) {
        return BaseResult.createResult(STATUS_SUCCESS, message, data);
    }

    public static BaseResult fail() {
        return BaseResult.createResult(STATUS_FAIL, "失败", null);
    }

    public static BaseResult fail(String message) {
        return BaseResult.createResult(STATUS_FAIL, message, null);
    }

    public static BaseResult fail(int code, String message) {
        return BaseResult.createResult(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static BaseResult createResult(int code, String message, Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }
}