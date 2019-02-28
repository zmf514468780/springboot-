package com.zmf.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.zmf.base.ResponseBase;
import com.zmf.constants.Constants;
import com.zmf.entity.UserEntity;
import com.zmf.feign.MemberServiceFeign;
import com.zmf.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 14:10
 * @Description:
 */
@Controller
@Slf4j
public class LoginController {
    private static final String LOGIN="login";
    private static final String INDEX = "redirect:/";
    private static final String qqrelation = "qqrelation";

    @Autowired
    private MemberServiceFeign memberServiceFeign;
    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(){
        return  LOGIN;
    }
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String login(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response){
        ResponseBase responseBase = memberServiceFeign.loginUser(userEntity);

        if( ! responseBase.getCode().equals(Constants.HTTP_RES_CODE_200)){ // 账号密码错误
            request.setAttribute("error","账号或密码错误，请重新输入后登录！");
            return LOGIN;
        }

        // Response内容:{"code":200,"data":{"token":"MEMBER_TOKENc57fd3fc-5560-4f5b-a003-23f42696f0b9"},"msg":"msg"}
       LinkedHashMap loginData = (LinkedHashMap) responseBase.getData();
       String token = (String) loginData.get("token");
       if(StringUtils.isEmpty(token)){
           request.setAttribute("error", "会话已经失效!");
           return LOGIN;
       }
       // 登录成功，将token 放置在session 中
        CookieUtils.addCookie(response,Constants.COOKIE_MEMBER_TOKEN,token,Constants.COOKIE_TOKEN_MEMBER_TIME);
        log.info( responseBase.getData()+" ########### cookie 增加成功！ ");
        // 这里在用token 去重新查询然后登录 不知道意义在那里。明白了意义 ， 这里可以不设置attribute 而让 （"/"）去设置。
        // return INDEX;
        // 改为以下代码。
        request.setAttribute("username", userEntity.getUsername());
        return "index" ;
    }
    // 生成qq授权登录链接
    @RequestMapping("/locaQQLogin")
    public String locaQQLogin(HttpServletRequest reqest) throws QQConnectException {
        String authorizeURL = new Oauth().getAuthorizeURL(reqest);
        return "redirect:" + authorizeURL;

    }
    @RequestMapping("/qqLoginCallback")
    public String qqLoginCallback(HttpServletRequest reqest, HttpServletResponse response, HttpSession httpSession) throws QQConnectException {

        // 1.获取授权码COde
        // 2.使用授权码Code获取accessToken
        AccessToken accessTokenOj = new Oauth().getAccessTokenByRequest(reqest);
        if (accessTokenOj == null) {
            reqest.setAttribute("error", "QQ授权失败");
            return "error";
        }
        String accessToken = accessTokenOj.getAccessToken();
        if (accessToken == null) {
            reqest.setAttribute("error", "accessToken为null");
            return "error";
        }
        // 3.使用accessToken获取openid
        OpenID openidOj = new OpenID(accessToken);
        String userOpenId = openidOj.getUserOpenID();
        // 4.调用会员服务接口 使用userOpenId 查找是否已经关联过账号
        Object memberServiceFegin;
        ResponseBase openUserBase = memberServiceFeign.findByOpenIdUser(userOpenId);
        if(openUserBase.getCode().equals(Constants.HTTP_RES_CODE_201)){
            // 5.如果没有关联账号，跳转到关联账号页面
            httpSession.setAttribute("qqOpenid", userOpenId);
            return qqrelation;
        }
        //6.已经绑定账号  自动登录 将用户token信息存放在cookie中
        LinkedHashMap dataTokenMap = (LinkedHashMap) openUserBase.getData();
        String memberToken=(String) dataTokenMap.get("token");
        setCookie(memberToken, response);

        return INDEX;
    }
    // 登录请求具体提交实现
    @RequestMapping(value = "/qqRelation", method = RequestMethod.POST)
    public String qqRelation(UserEntity userEntity, HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {
        // 1.获取openid
        String qqOpenid=(String) httpSession.getAttribute("qqOpenid");
        if(StringUtils.isEmpty(qqOpenid)){
            request.setAttribute("error", "没有获取到openid");
            return "error";
        }

        // 2.调用登录接口，获取token信息
        userEntity.setOpenid(qqOpenid);
        ResponseBase loginBase = memberServiceFeign.qqLogin(userEntity);

        if (!loginBase.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            request.setAttribute("error", "账号或者密码错误!");
            return LOGIN;
        }

        LinkedHashMap loginData = (LinkedHashMap) loginBase.getData();
        String memberToken = (String) loginData.get("token");
        if (StringUtils.isEmpty(memberToken)) {
            request.setAttribute("error", "会话已经失效!");
            return LOGIN;
        }
        // 3.将token信息存放在cookie里面
        setCookie(memberToken, response);
        return INDEX;
    }

    public void setCookie(String memberToken, HttpServletResponse response){
        CookieUtils.addCookie(response, Constants.COOKIE_MEMBER_TOKEN, memberToken, Constants.COOKIE_TOKEN_MEMBER_TIME);
    }
}

