package com.zmf.service;

import com.zmf.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 13:53
 * @Description:
 */
@RequestMapping("/test")
public interface TestService {
    //   public ResponseBase
    @RequestMapping("/testRest")
    public Map<String ,Object> testRest();

    @RequestMapping("/testResponse")
    public ResponseBase testResponse();

    @RequestMapping("/setRedisTest")
    public ResponseBase setRedisTest(String key, String value);

    @RequestMapping("/getRedis")
    public ResponseBase getRedis(String key);



}
