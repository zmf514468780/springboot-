package com.zmf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther: zmf
 * @Date: 2019-02-28 10:00
 * @Description:
 */
@Mapper
public interface OrderDao {
    @Update("update order_info set isPay=#{isPay} ,payId=#{aliPayId}, updated=now()  where orderNumber=#{orderNumber};")
    public int updateOrder(@Param("isPay") Long isPay, @Param("aliPayId") String aliPayId, @Param("orderNumber") String orderNumber);

}
