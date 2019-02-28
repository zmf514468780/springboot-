package com.zmf.controller;

import com.zmf.base.ResponseBase;
import com.zmf.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 11:00
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    private OrderFeign orderFeign;
    @RequestMapping("/textOrder")
    public ResponseBase textOrder(){
      return   orderFeign.updateOrderIdInfo((long)1,"1234567","cccccc");
    }
}
