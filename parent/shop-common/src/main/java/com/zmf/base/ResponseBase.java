package com.zmf.base;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 21:59
 * @Description:
 */
public class ResponseBase {
    // 响应code
    private Integer code;
    // 响应消息。
    private String msg;
    // 返回data
    private Object data;
    public ResponseBase(){

    }
    public ResponseBase(Integer code,String msg,Object data){

    this.code = code;
    this.msg = msg;
    this.data = data;

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
