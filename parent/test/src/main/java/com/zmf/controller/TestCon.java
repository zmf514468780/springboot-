package com.zmf.controller;

import com.alibaba.fastjson.JSONObject;
import com.zmf.base.TextMessage;
import com.zmf.utils.CheckUtil;
import com.zmf.utils.HttpClientUtil;
import com.zmf.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-22 10:59
 * @Description:
 */
@RestController
@Slf4j
public class TestCon {

    private static final String REQESTURL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(String signature, String timestamp, String nonce, String echostr){
        boolean checkSignature = CheckUtil.checkSignature(signature, timestamp, nonce);
        // 2.参数验证成功之后，返回随机数
        if (!checkSignature) {
            return null;
        }
        return echostr;
    }
    @RequestMapping("/indexs")
    public String index(){
        System.out.println("xxxx");
        return "index" ;
    }
    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public void index(HttpServletRequest reqest , HttpServletResponse response) throws Exception {

        reqest.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 1.将xml转换成Map格式
        Map<String, String> resultMap = XmlUtils.parseXml(reqest);
        log.info("###收到微信消息####resultMap:" + resultMap.toString());
        // 2.判断消息类型
        String msgType = resultMap.get("MsgType");
        // 3.如果是文本类型，返回结果给微信服务端
        PrintWriter writer = response.getWriter();
        switch (msgType) {
            case "text":
                // 开发者微信公众号
                String toUserName = resultMap.get("ToUserName");
                // 消息来自公众号
                String fromUserName = resultMap.get("FromUserName");
                // 消息内容
                String content = resultMap.get("Content");
                String resultJson = HttpClientUtil.doGet(REQESTURL + content);
                JSONObject jsonObject = JSONObject.parseObject(resultJson);
                Integer resultCode = jsonObject.getInteger("result");
                String msg = null;
                if (resultCode == 0) {
                    String resultContent = jsonObject.getString("content");
                    msg = setText(resultContent, toUserName,fromUserName);
                }else {
                    msg = setText("我现在有点忙.稍后回复您!", toUserName,fromUserName);
                }
                writer.println(msg);
                break;

            default:
                break;
        }
        writer.close();
    }
    public String setText(String content, String fromUserName, String toUserName) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(content);
        textMessage.setMsgType("text");
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setFromUserName(fromUserName);
        textMessage.setToUserName(toUserName);
        // 将实体类转换成xml格式
        String msg = XmlUtils.messageToXml(textMessage);
        log.info("####setTextMess()###messageToXml:" + msg);
        return msg;
    }
}
