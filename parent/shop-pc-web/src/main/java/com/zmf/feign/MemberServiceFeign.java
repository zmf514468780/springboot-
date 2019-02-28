package com.zmf.feign;

import com.zmf.service.MemberService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Auther: zmf
 * @Date: 2019-01-17 09:53
 * @Description:
 */
@FeignClient("member")
@Component
public interface MemberServiceFeign extends MemberService {

}
