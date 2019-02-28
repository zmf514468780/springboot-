package com.zmf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-22 15:42
 * @Description:
 */
@Controller
public class WechatLoginController {
    @RequestMapping("/wechatLogin")
    public String loginByWechat(HttpServletRequest request, Map<String, Object> map){
       String code = (String) request.getAttribute("code");
       String state = (String) request.getAttribute("state");
        if(code != null && !"".equals(code)) {
            // 授权成功, 微信获取用户openID
            OAuthInfo authInfo = null ;
//            OAuthInfo authInfo = WeiXinUtil.getAccess_token(code);
//            String openid = authInfo.getOpenid();
//            String access_token = authInfo.getAccess_token();
//
//            if(access_token == null) {
//                // Code 使用过 异常
//                System.out.println("Code 使用过 异常.....");
//                return "redirect:" + WeiXinUtil.getStartURLToGetCode();
//            }

            // 数据库中查询微信号是否绑定平台账号
             // 登录成功
            return "index";
        }
        // 未授权
//        return "redirect:" + WeiXinUtil.getStartURLToGetCode();
    return null ;
    }
}
