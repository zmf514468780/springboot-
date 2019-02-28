package com.zmf.mq;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.zmf.adapter.MessageAdapter;
import com.zmf.constants.Constants;
import com.zmf.email.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 14:18
 * @Description:
 */
@Component
@Slf4j
public class ConsumerDistribute {

    @Autowired
    private EmailService emailService;

    private MessageAdapter messageAdapter;

//    @Value("${messages.queue}")
//    private  String message ;

    // 这里改成怎么根据配置文件来创建队列。
    @JmsListener(destination = "message_queue")
    public void distribute(String json){
        log.info("#####消息服务平台接受消息内容:{}#####", json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject rootJSON = new JSONObject().parseObject(json);
        JSONObject header = rootJSON.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");

        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }
        // 判断接口类型是否为发邮件
        if (interfaceType.equals(Constants.MSG_EMAIL)) {
            messageAdapter = emailService;
        }
        if (messageAdapter == null) {
            return;
        }
        JSONObject contentJson = rootJSON.getJSONObject("content");
        log.info(contentJson.toString());
         messageAdapter.sendMsg(contentJson);
    }










}
