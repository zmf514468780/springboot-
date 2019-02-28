package com.zmf.entity;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 09:59
 * @Description:
 */
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderEntity {

    private Long id;
    /**
     * 用户userid
     */
    private Long userId;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 0 未支付 1已支付
     */
    private Integer isPay;
    /**
     * 支付id
     */
    private String payId;
    private Date created;
    private Date updated;
}
