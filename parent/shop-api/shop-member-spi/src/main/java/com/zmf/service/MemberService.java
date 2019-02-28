package com.zmf.service;

import com.zmf.base.ResponseBase;
import com.zmf.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 21:55
 * @Description:
 */
@RequestMapping("/member")
public interface MemberService {


    @RequestMapping("/findUserAll")
    public ResponseBase findUserAll();

    @RequestMapping("/registerUser")
   public ResponseBase registerUser(UserEntity userEntity);

    @RequestMapping("/loginUser")
    public ResponseBase loginUser(UserEntity userEntity);

    @RequestMapping("/findUserByToken")
    public ResponseBase findUserByToken(String token);

    //使用openid查找用户信息
    @RequestMapping("/findByOpenIdUser")
    ResponseBase findByOpenIdUser(@RequestParam("openid") String openid);
    // 用户登录
    @RequestMapping("/qqLogin")
    ResponseBase qqLogin(@RequestBody UserEntity user);
}

