# springboot电商项目
先启动61 服务器，在启动 62 63 64<br>
shop-parent :  pom.xml,parent，用于给后续pom依赖<br>
shop-api ：统一接口模块 端口：<br>
    shop-member-api：会员接口模块<br>
shop-common：工具模块<br>
shop-member：会员模块，端口：8762<br>
shop-message：消息模块  端口：8763  ,目前有邮件发送。<br>
Shop-pc-web: pcweb 现实模块。8764<br>
Shop-pay : 支付模块。8768 端口<br>
Shop-order:商品模块。8769 端口<br>
shop-eurake-server：springcloud eureka server 注册模块：用以启动eurake 服务，作为注册中心。 端口：8761 localhost:8761<br>
Pc端登录：http://localhost:8764/login<br>
其他需要启动的服务：<br>
   * 1.activamq ：http://127.0.0.1:8161/admin/         admin ； admin<br>
          cd Downloads/apache-activemq-5.15.8/bin/<br>
        zmf:bin zmf$ ./activemq start        <br>
   * 2.redis <br>
         zmf:bin zmf$ cd /usr/local/bin/<br>
      zmf:bin zmf$ sudo ./redis-server <br>


支付调试说明：insert order_info(userid,ordernumber,ispay,created) values(22,"cdsdcs232",0,now()) // cdsdcs232为商品号，需要每次修改，不能一致。<br>
            用postman调用获取token ，token作用时间为15分钟，= 该条订单可以在15分钟内支付，否则过期。<br>
localhost:8768/pay/createPayToken<br>
json格式为：<br>
{<br>
    "id":0,<br>
    "typeId":"1",<br>
    "orderId":"cdsdcs232",<br>
    "platformorderId":"",<br>
    "price":"500",<br>
    "source":"",<br>
    "state":"0",<br>
    "payMessage":""<br>
}<br>
3. 获取第二部返回的token码， 在打开链接 。localhost:8764/aliPay?payToken=TOKEN_PAY-1619090b-b73b-403a-a24e-a4fa361dce80 ；完成支付。流程已经ok。<br>

支付宝回调：同步、异步；同步：返回页面显示支付结果；异步：用以处理本系统支付完成后的业务。成功后需要向支付宝返回success，否则支付宝每隔一段时间会再次发送消息（25小时内发送8次。）<br>
