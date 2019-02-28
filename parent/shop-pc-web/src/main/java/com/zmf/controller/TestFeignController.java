package com.zmf.controller;

import com.zmf.base.BaseController;
import com.zmf.base.ResponseBase;
import com.zmf.entity.UserEntity;
import com.zmf.service.MemberService;
import com.zmf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 09:56
 * @Description:
 */
@RestController
public class TestFeignController extends BaseController {
    @Autowired
    private MemberService memberService;


    @RequestMapping("/testFeign")
    public ResponseBase testFeign() {
        return memberService.findUserAll();
    }


}
