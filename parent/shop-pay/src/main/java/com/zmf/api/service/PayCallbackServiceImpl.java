package com.zmf.api.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfig;
import com.zmf.api.entity.PaymentInfo;
import com.zmf.base.BaseController;
import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.dao.PaymentInfoDao;
import com.zmf.feign.OrderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 14:37
 * @Description:
 */
@Slf4j
@RestController
public class PayCallbackServiceImpl extends BaseController implements PayCallBackService {
    @Autowired
    private PaymentInfoDao paymentInfoDao;
    @Autowired
    private OrderFeign orderFeign;

    /**
     * 同步回调
     *
     * @param params
     * @return
     */
    @Override
    public ResponseBase synCallBack(@RequestParam Map<String, String> params) {

        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
            log.info("####同步回调开始####params:", params);
            //——请在这里编写您的程序（以下代码仅作参考）——
            if (signVerified) {
                //商户订单号
                // 商户订单号
                String out_trade_no = params.get("out_trade_no");
                // 支付宝交易号
                String trade_no = params.get("trade_no");
                // 付款金额
                String total_amount = params.get("total_amount");
                JSONObject data = new JSONObject();
                data.put("out_trade_no", out_trade_no);
                data.put("trade_no", trade_no);
                data.put("total_amount", total_amount);
                return setResultSuccess(data);
                //   out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            } else {
                return setResultError("验签失败");
                //  out.println("验签失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return setResultError("系统错误！");
        } finally {
            log.info("####同步回调结束####params:", params);
        }


    }

    /**
     * 同步回调
     *
     * @param params
     * @return
     */
    @Override
    public String asynCallBack(@RequestParam Map<String, String> params) {


        try {
            log.info("####异步回调开始####params:", params);
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);

            //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/

            if (!signVerified) { // 如果验证失败 直接返回fail
                return "fail";
            }

            String outTradeNo = params.get("out_trade_no");// 商品订单号
            //支付宝交易号
           // String trade_no = params.get("trade_no");
            //交易状态
            // String trade_status = params.get("trade_status");

            PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(outTradeNo);
            if(paymentInfo == null){
                return "fail";
            }
           int state = paymentInfo.getState(); //支付宝 再次发送的时候判断是否已经处理过，理论上不会同时执行，因为支付宝有时间间隔
            if(state == 1){
                return "success";
            }
            //支付宝交易号
             String tradeNo = params.get("trade_no");
            // 修改 支付表状态
            paymentInfo.setState(1);// 标识为已经支付
            paymentInfo.setPayMessage(params.toString());
            paymentInfo.setPlatformorderId(tradeNo);
            Integer updateResult = paymentInfoDao.updatePayInfo(paymentInfo);
            if(updateResult <= 0 ){
                return "fail";
            }
            ResponseBase orderResult = orderFeign.updateOrderIdInfo(1l, tradeNo, outTradeNo);// 更新订单状态
            if(orderResult.getCode() != Constants.HTTP_RES_CODE_200) {
                return "fail";
            }
            return "success";
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "fail";
        }finally {
            log.info("####异步回调结束####params:", params);
        }

    }

}
