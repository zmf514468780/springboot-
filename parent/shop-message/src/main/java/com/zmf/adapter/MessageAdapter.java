package com.zmf.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 14:12
 * @Description:
 */
public interface MessageAdapter {
    void sendMsg(JSONObject data);
}
