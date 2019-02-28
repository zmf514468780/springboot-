package com.zmf.controller;

import org.apache.commons.httpclient.HttpURL;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Auther: zmf
 * @Date: 2019-01-22 14:59
 * @Description:
 */
public class UrlEncode {
    public static void main(String[] args) {
      String url  =  URLEncoder.encode("http://84253cbe.ngrok.io/indexs");
        System.out.println(url);
        System.out.println(URLDecoder.decode(url));

    }
}
