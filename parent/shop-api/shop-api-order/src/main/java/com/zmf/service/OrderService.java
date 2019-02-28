package com.zmf.service;

import com.zmf.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 09:59
 * @Description:
 */
@RequestMapping("/order")
public interface OrderService {

    @RequestMapping("/updateOrderIdInfo")
    ResponseBase updateOrderIdInfo(@RequestParam("isPay") Long isPay, @RequestParam("payId") String payId,
                                   @RequestParam("orderNumber") String orderNumber);
}
