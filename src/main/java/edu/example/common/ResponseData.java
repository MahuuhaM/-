package edu.example.common;


import com.google.gson.Gson;

public class ResponseData<T> {
    /*状态码, 用来返回操作的结果*/
    private Integer status;
    /*返回的数据*/
    private T data;
    /*返回给前台的信息*/
    private String message;
    private static Gson gson;
    static {
        gson = new Gson();
    }
    private ResponseData() {

    }

    public ResponseData(Integer status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    /*操作成果返回返回这个*/
    public String toSuccess(T data, String message) {
        return gson.toJson(new ResponseData<>(1, data, message));
    }
    public String toSuccess() {
        return gson.toJson(new ResponseData<>(1, null, "操作成功"));
    }
    public String toSuccess(String message) {
        return gson.toJson(new ResponseData<>(1, null, message));
    }

    /*操作失败调用*/
    public String toError() {
        return gson.toJson(new ResponseData<>(0, null, "操作失败"));
    }
    public String toError(T data) {
        return gson.toJson(new ResponseData<>(0, data, "操作失败"));
    }
    public String toError(String message) {
        return gson.toJson(new ResponseData<>(0, null, "操作失败"));
    }

    /*自定义返回内容*/
    public String toDomain(Integer status, T data, String message) {
        return gson.toJson(new ResponseData<>(status, data, message));
    }
}
