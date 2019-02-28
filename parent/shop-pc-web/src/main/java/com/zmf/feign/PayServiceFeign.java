
package com.zmf.feign;
/**
 * @Auther: zmf
 * @Date: 2019-02-27 13:54
 * @Description:
 */

import com.zmf.api.service.PayService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 13:40
 * @Description:
 */
@FeignClient("pay")
@Component
public interface PayServiceFeign  extends PayService {
}
