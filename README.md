# springboot电商项目
先启动61 服务器，在启动 62 63 64
shop-parent :  pom.xml,parent，用于给后续pom依赖
shop-api ：统一接口模块 端口：
    shop-member-api：会员接口模块
shop-common：工具模块
shop-member：会员模块，端口：8762
shop-message：消息模块  端口：8763  ,目前有邮件发送。
Shop-pc-web: pcweb 现实模块。8764
Shop-pay : 支付模块。8768 端口
Shop-order:商品模块。8769 端口
shop-eurake-server：springcloud eureka server 注册模块：用以启动eurake 服务，作为注册中心。 端口：8761 localhost:8761
Pc端登录：http://localhost:8764/login
其他需要启动的服务：
    1.activamq ：http://127.0.0.1:8161/admin/         admin ； admin
          cd Downloads/apache-activemq-5.15.8/bin/
        zmf:bin zmf$ ./activemq start        
    2.redis 
         zmf:bin zmf$ cd /usr/local/bin/
      zmf:bin zmf$ sudo ./redis-server 


支付调试说明：insert order_info(userid,ordernumber,ispay,created) values(22,"cdsdcs232",0,now()) // cdsdcs232为商品号，需要每次修改，不能一致。
            用postman调用获取token ，token作用时间为15分钟，= 该条订单可以在15分钟内支付，否则过期。
localhost:8768/pay/createPayToken
json格式为：
{
    "id":0,
    "typeId":"1",
    "orderId":"cdsdcs232",
    "platformorderId":"",
    "price":"500",
    "source":"",
    "state":"0",
    "payMessage":""
}
3. 获取第二部返回的token码， 在打开链接 。localhost:8764/aliPay?payToken=TOKEN_PAY-1619090b-b73b-403a-a24e-a4fa361dce80 ；完成支付。流程已经ok。

支付宝回调：同步、异步；同步：返回页面显示支付结果；异步：用以处理本系统支付完成后的业务。成功后需要向支付宝返回success，否则支付宝每隔一段时间会再次发送消息（25小时内发送8次。）
