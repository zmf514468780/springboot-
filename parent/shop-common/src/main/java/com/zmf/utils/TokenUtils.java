package com.zmf.utils;

import com.zmf.constants.Constants;

import java.util.UUID;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 16:32
 * @Description:
 * 生产token码的util 类。
 */
public class TokenUtils {
    public  static String getMemberToken(){
        return Constants.TOKEN_NUMBER + UUID.randomUUID();
    }
    public static String getPayToken(){
        return Constants.TOKEN_PAY + "-" + UUID.randomUUID();
    }
}
