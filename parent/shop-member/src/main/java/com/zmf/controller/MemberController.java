package com.zmf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 23:48
 * @Description:
 */
@RestController
public class MemberController {
    @RequestMapping("/index")
    public String getIndex(){
        return "success";
    }
}
