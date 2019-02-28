package com.zmf.feign;

import com.zmf.service.OrderService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 10:52
 * @Description:
 */
@Component
@FeignClient("order")
public interface OrderFeign extends OrderService {

}