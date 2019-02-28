package com.zmf.service.impl;

import com.netflix.discovery.converters.Auto;
import com.zmf.base.BaseController;
import com.zmf.base.ResponseBase;
import com.zmf.dao.OrderDao;
import com.zmf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 10:02
 * @Description:
 */
@RestController
public class OrderServiceImpl extends BaseController implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public ResponseBase updateOrderIdInfo(Long isPay, String payId, String orderNumber) {

        int result = orderDao.updateOrder(isPay, payId, orderNumber);
        if (result > 0) {
            return setResultSuccess();
        }
        return setResultError("系统错误!");
    }
}
