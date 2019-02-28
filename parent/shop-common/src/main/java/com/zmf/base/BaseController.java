package com.zmf.base;

import com.zmf.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Auther: zmf
 * @Date: 2019-01-15 21:58
 * @Description: 这是 返回结构体。
 */
@Component
public class BaseController {
    @Autowired
    protected BaseRedisService baseRedisService;
    public ResponseBase setResultError(Integer code,String msg) {
        return setResult(code, msg, null);
    }
    // 返回成功，data 为null
    public ResponseBase setResultSuccess() {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_MSG, null
        );
    }

    // 返回成功
    public ResponseBase setResultSuccess(Object data ) {
        return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_MSG, data
        );
    }
    // 返回失败
    public ResponseBase setResultError(String msg) {
        return setResult(Constants.HTTP_RES_CODE_500, msg, null
        );
    }
    public ResponseBase setResult(Integer code, String msg, Object data){
        ResponseBase responseBase = new ResponseBase();
        responseBase.setCode(code);
        responseBase.setMsg(msg);
        if(data != null){
            responseBase.setData(data);
        }
        return responseBase;
    }
}
