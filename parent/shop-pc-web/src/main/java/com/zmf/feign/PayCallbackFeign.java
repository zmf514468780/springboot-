package com.zmf.feign;

import com.zmf.api.service.PayCallBackService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 15:32
 * @Description:
 */
@FeignClient("pay")
@Component
public interface PayCallbackFeign extends PayCallBackService {
}
