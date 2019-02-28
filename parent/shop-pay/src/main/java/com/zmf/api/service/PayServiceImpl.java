package com.zmf.api.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.google.gson.JsonObject;
import com.zmf.api.entity.PaymentInfo;
import com.zmf.base.BaseController;
import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.dao.PaymentInfoDao;
import com.zmf.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 09:48
 * @Description: localhost:8768/pay/createPayToken
 */
@RestController
public class PayServiceImpl extends BaseController implements PayService {
    @Autowired
    PaymentInfoDao paymentInfoDao;

    /***
     * {
     * 	"id":1,
     * 	"typeId":"2",
     * 	"orderId":"3",
     * 	"platformorderId":"",
     * 	"price":"500",
     * 	"source":"",
     * 	"state":"0",
     * 	"payMessage":""
     *
     * }
     *
     *
     *  创建token 代码用这个token去调用支付宝申请
     * @param paymentInfo
     * @return
     */
    @RequestMapping("/createPayToken")
    @Override
    public ResponseBase createToken(@RequestBody PaymentInfo paymentInfo) {
        // 1. 创建支付请求申请
        Integer savePaymentType = paymentInfoDao.savePaymentType(paymentInfo);
        //2.生成对应的token
        if (savePaymentType < 0) {
            return setResultError("创建订单支付失败！");
        }
        //3.存放在redis中，key为token，value 为支付id
        String token = TokenUtils.getPayToken();
        baseRedisService.setString(token, paymentInfo.getId() + "", Constants.PAY_TOKEN_MEMBER_TIME);
        //4.返回token
        JSONObject data = new JSONObject();
        data.put("payToken", token);
        return setResultSuccess(data);
    }

    /***
     *  使用支付令牌查找支付信息
     * @param payToken
     * @return
     */
    @RequestMapping("/findPayToken")
    @Override
    public ResponseBase findPayToken(@RequestParam("payToken") String payToken) {
        if (StringUtils.isEmpty(payToken)) {
            return setResultError("token 不能为空！");
        }
        //判断token 有效期
        String payId = baseRedisService.getString(payToken);
        if (StringUtils.isEmpty(payId)) {
            return setResultError("token已经过期");
        }
        Long payIDL = Long.parseLong(payId); // mysql 库的payID，自增的。
        PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(payIDL);
        if (paymentInfo == null) {
            return setResultError("未找到支付信息！");
        }
        // 6.对接支付代码 返回提交支付from表单元素给客户端
//获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = paymentInfo.getOrderId();
        //付款金额，必填

        String total_amount = paymentInfo.getPrice() + "";
        //订单名称，必填
        String subject = "订单名称";
        //商品描述，可空
        String body = "zmf 付款demo";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        try {
            String result = alipayClient.pageExecute(alipayRequest).getBody();
            JSONObject data = new JSONObject();
            data.put("putHtml", result);
            return setResultSuccess(data);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return setResultError("支付异常");
        }

        //输出


    }
}
