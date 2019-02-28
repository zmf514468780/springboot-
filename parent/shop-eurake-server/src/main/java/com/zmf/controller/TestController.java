package com.zmf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zmf
 * @Date: 2019-01-22 09:24
 * @Description:
 */
@RestController
public class TestController {
    @RequestMapping("/index")
    public String index(){
        return "index zmf  cccccc " ;
    }
}
