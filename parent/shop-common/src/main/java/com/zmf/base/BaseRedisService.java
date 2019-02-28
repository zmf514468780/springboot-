package com.zmf.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 22:15
 * @Description: redis 增加以及删除节点。
 */
@Component
public class BaseRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public  void setString(String key,Object data){
        setString(key,data,null);
    }
    public void setString(String key,Object data,Long timeout){
        if(data instanceof  String){
            String value = (String) data;
            stringRedisTemplate.opsForValue().set(key,value);
        }
        if(timeout != null){
            stringRedisTemplate.expire(key,timeout, TimeUnit.SECONDS);

        }
    }
    public void delKey(String key){
        stringRedisTemplate.delete(key);
    }

    public String getString(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }
}
