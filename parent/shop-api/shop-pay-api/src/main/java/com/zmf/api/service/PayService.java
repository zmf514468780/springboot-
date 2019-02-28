package com.zmf.api.service;

import com.zmf.api.entity.PaymentInfo;
import com.zmf.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 09:37
 * @Description:
 */
@RequestMapping("/pay")
public interface PayService {
    //创建支付令牌
    @RequestMapping("/createPayToken")
    public ResponseBase createToken(@RequestBody PaymentInfo PaymentInfo);
    // 使用支付令牌查找支付信息
    @RequestMapping("/findPayToken")
    public ResponseBase findPayToken (@RequestParam("payToken") String  payToken);
}
