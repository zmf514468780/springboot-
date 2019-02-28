package com.zmf.api.service;

import com.zmf.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 14:34
 * @Description:
 */
@RequestMapping("/callBack")
public interface PayCallBackService {
    // // 同步回调
    @RequestMapping("/synCallBackService")
    public ResponseBase synCallBack(@RequestParam Map<String, String> params);

    // // 异步回调
    @RequestMapping("/asynCallBackService")
    public String asynCallBack(@RequestParam Map<String, String> params) ;
}
