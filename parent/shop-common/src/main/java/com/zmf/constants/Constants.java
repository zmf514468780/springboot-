package com.zmf.constants;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 22:05
 * @Description: 类文件
 * <p>
 * // shift + command +x 实现大小写转换。
 */
public class Constants {
    // 响应code
    public static final String HTTP_RES_CODE_NAME = "code";
    // 响应msg
    public static final String HTTP_RES_CODE_MSG = "msg";
    // 响应data
    public static final String HTTP_RES_CODE_DATA = "data";

    // 响应请求成功
    public static final String HTTP_RES_CODE_SUCCESS = "success";
    // 系统错误
    public static final String HTTP_RES_CODE_ERROR = "fail";
    // 响应成功code
    public static final int HTTP_RES_CODE_200 = 200;
    // 系统错误
    public static final int HTTP_RES_CODE_500 = 500;

    public static final String MSG_EMAIL = "email";
    public static final Integer HTTP_RES_CODE_201 = 201;
    public static final String TOKEN_PAY = "TOKEN_PAY";
    public static final Long PAY_TOKEN_MEMBER_TIME = (long)  (60 * 15 );

    public static String TOKEN_NUMBER = "MEMBER_TOKEN";
    // 用户有效期 90天
    public static final  Long TOKEN_MEMBER_TIME = (long) (60 * 60 * 24 * 90);
    public static final  int COOKIE_TOKEN_MEMBER_TIME = (60 * 60 * 24 * 90);
    // cookie 会员 totoken 名称
    public static final  String COOKIE_MEMBER_TOKEN = "cookie_member_token";
}
