package com.zmf.service;

import com.zmf.base.BaseController;
import com.zmf.base.BaseRedisService;
import com.zmf.base.ResponseBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 13:54
 * @Description: 这是一个测试类。
 */
@RestController
@Slf4j
public class TestServiceImpl extends BaseController implements TestService {
    @Autowired
    private BaseRedisService baseRedisService;
    @Override
    public Map<String, Object> testRest() {
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        return map;
    }

    @Override
    public ResponseBase testResponse() {
        return setResultSuccess();
    }

    @Override
    public ResponseBase setRedisTest(String key, String value) {

        baseRedisService.setString(key,value);
        return setResultSuccess();
    }


    @Override
    public ResponseBase getRedis(@PathParam("key") String key) {
        String value = baseRedisService.getString(key);
        log.info("===============");
        log.info(value+"  " + key);
        log.info("===============");
        return setResultSuccess(value);
    }

}
