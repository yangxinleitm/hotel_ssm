package cn.hotel.service.utils;

import java.io.Serializable;

public class RestModel implements Serializable {
    private static final long serialVersionUID = -4497382838074190558L;
    public static final String CODE_SUCCESS = "200";
    public static final String MESSAGE_SUCCESS = "成功";
    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RestModel() {
    }

    public RestModel(Object data) {
        this.code = "200";
        this.message = "";
        this.data = data;
    }

    public RestModel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestModel(String code, Object data) {
        this.code = code;
        this.message = "";
        this.data = data;
    }

    public RestModel(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
