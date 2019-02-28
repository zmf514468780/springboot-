package com.zmf.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 14:36
 * @Description:
 */
@Service
public class RegisterMailboxProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    public void sendMsg(Destination destination, String json) {
        jmsMessagingTemplate.convertAndSend(destination, json);
    }
}
