package com.zmf.controller;

import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.entity.UserEntity;
import com.zmf.feign.MemberServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 11:02
 * @Description: 注册登录页面
 */
@Controller
@Slf4j
public class RegisterController {
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    private static final String REGISTER = "register";
    private static final String LOGIN = "login";

    // 跳转到注册页面。
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet() {
        return REGISTER;
    }

    /***
     *  前台不是json 传输 所以不要加@RequestBody
     * @param userEntity
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost( UserEntity userEntity, HttpServletRequest request) {
        log.info("========开始注册========");
        userEntity.setCreated(new Date());
        userEntity.setUpdated(new Date());
        ResponseBase responseBase = memberServiceFeign.registerUser(userEntity);

        if (! responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            request.setAttribute("error","注册失败！");
            // 如果注册失败，重新注册
              return     REGISTER;
        }
        log.info("========注册成功,跳转到登录页面========");
        return LOGIN;
    }

}
