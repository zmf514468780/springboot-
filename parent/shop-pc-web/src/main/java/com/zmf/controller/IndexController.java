package com.zmf.controller;

import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.feign.MemberServiceFeign;
import com.zmf.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 09:50
 * @Description:
 * 跳转 controller
 */
@Controller
@Slf4j
public class IndexController {
    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/getIndex")
    public String getIndex(){
        return "index" ;
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        // 从cookie 中获取session 信息。
        Cookie[] cookies =request.getCookies();

        String token =  CookieUtils.getCookieValue(request, Constants.COOKIE_MEMBER_TOKEN);
        // 判断token 是否过期
        if(! StringUtils.isEmpty(token)){//如果不为空
            ResponseBase userByToken = memberServiceFeign.findUserByToken(token);  // 获取到的token 值。
            Map userData = (LinkedHashMap) userByToken.getData();
            String username = (String) userData.get("username");
            request.setAttribute("username", username);
        }

         return "index";
    }

}
