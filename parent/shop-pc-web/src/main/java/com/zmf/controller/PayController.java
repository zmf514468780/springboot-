package com.zmf.controller;

import com.zmf.api.service.PayCallBackService;
import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.feign.PayCallbackFeign;
import com.zmf.feign.PayServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 13:38
 * @Description:
 */
@Slf4j
@Controller
public class PayController {
    // 同步返回的页面
    private static final String PAY_SUCCESS = "pay_success";
    @Autowired
    private PayServiceFeign payServiceFeign;
    @Autowired
    private PayCallbackFeign payCallbackFeign;
    @RequestMapping("/aliPay")
    public void aliPay(String payToken, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (StringUtils.isEmpty(payToken)) {
            return;
        }
        ResponseBase responseBase = payServiceFeign.findPayToken(payToken);
        if (Constants.HTTP_RES_CODE_200 != responseBase.getCode()) {
            writer.println(responseBase.getMsg());
            return;
        }
        LinkedHashMap map = (LinkedHashMap) responseBase.getData();
        String putHtml = (String) map.get("putHtml");
        writer.println(putHtml);
        writer.close();
    }
    @RequestMapping("/callBack/synCallBack")
    public void synCallBack(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //  获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        ResponseBase responseBase = payCallbackFeign.synCallBack(params);
        if(Constants.HTTP_RES_CODE_200 != responseBase.getCode()){
          //  return "error";
            return ;
        }
        LinkedHashMap<String,String> data = (LinkedHashMap<String, String>) responseBase.getData();
//        request.setAttribute("out_trade_no",data.get("out_trade_no"));
//        request.setAttribute("trade_no",data.get("trade_no"));
//        request.setAttribute("total_amount",data.get("total_amount"));
//        return PAY_SUCCESS;
    String payHtml=" <form name='punchout_form' method='post' action='http://127.0.0.1:8764/callBack/synSuccessPage'>"
            + "<input type='hidden' name='outTradeNo' value='" + data.get("out_trade_no") + "'>"
            + "<input type='hidden' name='tradeNo' value='" + data.get("trade_no") + "'>"
            + "<input type='hidden' name='totalAmount' value='" + data.get("total_amount") + "'>"
            +"</form>" +
            "<script>document.forms[0].submit();</script>";

        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer  = response.getWriter();
            writer.println(payHtml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //一下解决 浏览器显示参数问题
    }

    @RequestMapping(value = "/callBack/synSuccessPage", method = RequestMethod.POST)
    public String synSuccessPage(String outTradeNo, String tradeNo, String totalAmount,HttpServletRequest request){
        log.info("synSuccessPage方法开始调用。。");
        request.setAttribute("out_trade_no",outTradeNo);
        request.setAttribute("trade_no",tradeNo);
        request.setAttribute("total_amount",totalAmount);
        log.info("synSuccessPage方法调用结束。。");
     return PAY_SUCCESS;
    }
    @RequestMapping("/callBack/asynCallBack")
    @ResponseBody
    public String asynCallBack(HttpServletRequest request) throws UnsupportedEncodingException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
          //  valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return payCallbackFeign.asynCallBack(params);

    }
}
