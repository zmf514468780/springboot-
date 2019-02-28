package com.zmf.service;

import com.alibaba.fastjson.JSONObject;
import com.zmf.base.BaseController;
import com.zmf.base.BaseRedisService;
import com.zmf.base.ResponseBase;

import com.zmf.constants.Constants;
import com.zmf.dao.MemberDao;
import com.zmf.entity.UserEntity;
import com.zmf.mq.RegisterMailboxProducer;
import com.zmf.utils.MD5Utils;
import com.zmf.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-01-15 23:36
 * @Description:
 */
@Slf4j
@RestController
public class MemberServiceImpl extends BaseController implements MemberService {
    @Autowired
    private BaseRedisService baseRedisService;

    @Autowired
     private MemberDao memberDao ; // 这里可以成功注入。

    @Value("${messages.queue}")
    private String MESSAGESQUEUE;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Override
    public ResponseBase findUserAll() {

       List<UserEntity> userList = memberDao.findAll();
       log.info(userList.toString());
        return setResultSuccess(userList);
    }


    /***
     *  要加 @RequestBody 来实现json 对UserEntity的实体对应。
     * @param userEntity
     * @return
     */
    @Override
    public ResponseBase registerUser(@RequestBody UserEntity userEntity) {

        userEntity.setPassword(MD5Utils.MD5(userEntity.getPassword()));
        Integer result = memberDao.insertUser(userEntity);

        if(result > 0 ){
            log.info("###############用户注册成功,开始发送消息！##############");
            // 这里要向队列发送消息

            String email = userEntity.getEmail();
            String json = emailJson(email);
            sendMessage(json);
            log.info("###############用户注册成功,发送消息结束！##############");
            return setResultSuccess("用户注册成功");
        }
        return setResultError("用户注册失败！");
    }

    /**
     *  用户登录
     * @param user
     * @return
     */
    @Override
    public ResponseBase loginUser(@RequestBody  UserEntity user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(password == null || StringUtils.isEmpty(password) || username == null ||StringUtils.isEmpty(username) ){
            return setResultError("用户名或密码为空！");
        }
        password  = MD5Utils.MD5(password);
       UserEntity userEntity =  memberDao.findByUsernameAndPassword(username,password);
       if(userEntity == null){
           return setResultError("用户名或密码错误！");
       }
       // 生产token码
        String token = TokenUtils.getMemberToken();
        baseRedisService.setString(token,userEntity.getId()+"",null); // 保存在redis中。

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token",token);
        return setResultSuccess(jsonObject);
    }

    @Override
    public ResponseBase findUserByToken(@RequestBody String token) {
        if(token == null || StringUtils.isEmpty(token)){
            return setResultError("token 为空！");
        }
        String value = baseRedisService.getString(token);

      //   String id = baseRedisService.getString(token);
        log.info("=======这里是userid==========");
        log.info(value+"  token : "+ token);
        log.info("=======这里是userid==========");
        if(StringUtils.isEmpty(value ) || value  == null){
            return  setResultError("未查到用户信息！");
        }
        UserEntity user = memberDao.findByID(Long.parseLong(value));
        user.setPassword(null);
        return setResultSuccess(user);
    }

    @Override
    public ResponseBase findByOpenIdUser(@RequestParam("openid") String openid) {
        // 1.验证参数
        if (StringUtils.isEmpty(openid)) {
            return setResultError("openid不能为空1");
        }
        // 2.使用openid 查询数据库 user表对应数据信息
        UserEntity userEntity = memberDao.findByOpenIdUser(openid);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_201, "该openid没有关联");
        }
        // 3.自动登录
        return setLogin(userEntity);
    }

    @Override
    public ResponseBase qqLogin(@RequestBody UserEntity user) {
        // 1.验证参数
        String openid = user.getOpenid();
        if (StringUtils.isEmpty(openid)) {
            return setResultError("openid不能为空!");
        }
        // 2.先进行账号登录
        ResponseBase setLogin = loginUser(user);
        if (!setLogin.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setLogin;
        }
        // 3.自动登录
        JSONObject jsonObjcet = (JSONObject) setLogin.getData();
        log.info(jsonObjcet+" ");
        // 4. 获取token信息
        String memberToken = jsonObjcet.getString("token");
        ResponseBase userToken = findUserByToken(memberToken);

        if (!userToken.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return userToken;
        }
        UserEntity userEntity = (com.zmf.entity.UserEntity) userToken.getData();
        // 5.修改用户openid
        Integer userId = userEntity.getId();

        Integer updateByOpenIdUser = memberDao.updateByOpenIdUser(openid, userId);
        if (updateByOpenIdUser <= 0) {
            return setResultError("QQ账号管理失败!");
        }
        return setLogin;
    }

    private ResponseBase setLogin(UserEntity userEntity) {
        if (userEntity == null) {
            return setResultError("账号或者密码不能正确");
        }
        // 3.如果账号密码正确，对应生成token
        String memberToken = TokenUtils.getMemberToken();
        // 4.存放在redis中，key为token value 为 userid
        Integer userId = userEntity.getId();
        log.info("####用户信息token存放在redis中... key为:{},value", memberToken, userId);
        baseRedisService.setString(memberToken, userId + "", Constants.TOKEN_MEMBER_TIME);
        // 5.直接返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", memberToken);
        return setResultSuccess(jsonObject);
    }

    private String emailJson(String email){
        JSONObject rootJson = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", Constants.MSG_EMAIL);
        JSONObject content = new JSONObject();
        content.put("email", email);
        rootJson.put("header", header);
        rootJson.put("content", content);
        return rootJson.toJSONString();
    }

    private void sendMessage(String json){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
        registerMailboxProducer.sendMsg(activeMQQueue,json);

    }
}
