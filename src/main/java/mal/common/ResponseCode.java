package com.mal.common;

/*
    封装扩展，想要什么错误扩展！
 */
public enum  ResponseCode {

    //成功请求
    SUCCESS(0,"SUCCESS"),
    //错误请求
    ERROR(1,"ERROR"),
    //需要登录
    NEED_LOGIN(10,"NEED_LOGIN"),
    //非法的请求
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");


    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc =desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
