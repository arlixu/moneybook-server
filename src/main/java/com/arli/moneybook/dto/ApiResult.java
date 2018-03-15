package com.arli.moneybook.dto;
import java.io.Serializable;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -4781082011605001370L;
    private String code;
    private String action;
    private String msg;
    private T data;
    private Boolean status = false;

    public static <T> ApiResult<T> fail(String action, String msg) {
        ApiResult<T> apiResult = new ApiResult();
        apiResult.status = false;
        apiResult.msg = msg;
        apiResult.action = action;
        if (apiResult.status.booleanValue()) {
            apiResult.code = "0";
        } else {
            apiResult.code = "-1";
        }

        return apiResult;
    }

    public static <T> ApiResult<T> success(String action, T data) {
        ApiResult<T> apiResult = new ApiResult();
        apiResult.code = "0";
        apiResult.data = data;
        apiResult.action = action;
        apiResult.status = true;
        return apiResult;
    }

    public ApiResult() {
    }

    public ApiResult(String action) {
        this.code = "0";
        this.msg = "success";
        this.data = null;
        this.action = action;
        this.status = true;
    }

    public ApiResult(Boolean status, String action) {
        this.status = status;
        this.msg = status.booleanValue() ? "success" : "alreay_finish";
        this.action = action;
        if (status.booleanValue()) {
            this.code = "0";
        } else {
            this.code = "-1";
        }

    }

    public ApiResult(String code, String action, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.action = action;
        if ("-1".equals(code)) {
            this.status = false;
        } else {
            this.status = true;
        }

    }

    public ApiResult(String action, T data) {
        this.code = "0";
        this.msg = "success";
        this.data = data;
        this.action = action;
        this.status = true;
    }

    public String toString() {
        return "ApiResult{code='" + this.code + '\'' + ", action='" + this.action + '\'' + ", msg='" + this.msg + '\'' + ", data=" + this.data + ", status=" + this.status + '}';
    }

    public String getCode() {
        return this.code;
    }

    public String getAction() {
        return this.action;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public ApiResult setCode(String code) {
        this.code = code;
        return this;
    }

    public ApiResult setAction(String action) {
        this.action = action;
        return this;
    }

    public ApiResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ApiResult setData(T data) {
        this.data = data;
        return this;
    }

    public ApiResult setStatus(Boolean status) {
        this.status = status;
        return this;
    }
}