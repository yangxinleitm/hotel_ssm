package cn.hotel.controller.utils;

public class JsonModel {
    private boolean status;
    private String message;
    private Object result;

    public JsonModel() {
    }

    public boolean isStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getResult() {
        return this.result;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
