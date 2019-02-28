package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: zmf
 * @Date: 2019-02-27 09:42
 * @Description:
 */


public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092800615553";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCM64zI8DaaDYMiXiH1sTKKCwg9pVUHi56OmGATzFH7yRqXjryOdegHCA1KIX029d2oUVeIQ6EGK7Nalbp9jbaTV0gtqFcVbMDdpYl8KnwaSuUeHjGLnVFnAFd52F+AX2TecQxPsRt5z/vEil3v++z33oFWvwwSvbEBnG2+DJ3hXzspXaih9osigbqLSI7QTfkRPGtulHv5WV3bCkNYW9YxAI+61ZHQCeLaVHNBYKdi18zFocokXVy31xhkjZnICuymfdQujYr/TCK4kOKkUsZLOXl9WG0mkP8GfBmRnusRV+vkVz2PTEuMY7RXzl55zSwe36cDSuwr93ISvvxPx6kpAgMBAAECggEAZtoxGiPBJnVAReBAlwtq4Lgmnp/CTMyJwHfUf/67w7LHFuEWHEBQRnmJAZcpxehzjLs50PnTWCajNDVeNO8u16F3y+M3FCsZJW0ByL9VbvdE/NN6w/ThifUE46iaI578hyIpoyyX6jN0BcIbx+Gu1kbvTboI/NC4lX1v16trvCZnBJjkz9TfsGil/Xqe+DPgi09ecJdx/ghiANGqE10PkohFBDoTTzJvcd3XghqwBFXt1Y7FhvDF2gzocqSeVTX3BlzRniz+7bEDiF+D3h//9kLip+mHnYF0UssLBf0bocvDa/VUMv3OJurSGGI9sNZbmxCfETXXO4q0lzef/p0NJQKBgQDX6rW8vX/tsSgJ4ee4QmS9gwdvKGyIZiadyRukdEbrOD6D8w2IiE7kM9y/GkiB3JsHn7MTbMYorsqK3F8JXfAiZOWF8iIy6fRKX+F0G4JA9MUZyiDHSSbbC7mWsSi4wKkg98JXEsa2duBVCe/P2VAHVBBngmw64575nWv+AC5gkwKBgQCnFKz6E5rjRKjkOh5b7QxXwmDXN6MG98lEt5ZqajYuO10opA0fKxEWkmbOO6qLE9sQXgXnPq7cKZ3w/V4vmTodISWaRs883BJ7ip5mi7+EaRHKkad+3XJEBpSPcsiN748LMSAdUMJEc/bv2/55zVJn74G2rn2NW7WT6Kx3WtOw0wKBgFQIEsbr8XfudKiz9NzvagmfjyshzmufZfQOquVsI5W0BBCSbXfvpNSHoqf1cLapxuRGsQkkUlLPBCDa91nL54NbRUDGJa0vBFZVzobDlgP1vyf0VKF6xNaHT6loNXMB+FDOXCJLOm8VAOXXwi9gYhcYDnJafgjFQb/gFoF6TJJlAoGAGNQmvGbrbniGjJviZRC989Cr6W+YJ3HtE+wgSyILeSVTO7XdSVNyD6Oj6WPOfSpvNGh0gh+7hnYcxXIxa+teSnPhjaKWK1oc2QUJcv2zcS7X+P1aIa0wzNEZhJOvIACIhkY/mnuk0t1UxeIwV9NeOpnJ8ebOO5J0Uk2SS45QxQcCgYA34ptRVYDgPyY0D7LvHLVX0dInoSLvuYFGyIjz7v8VmX80GI7h6CiyY6+1L0xGBORupM/PXrSbKeHqH/8FUqr3b6m4+3AmHr2OaixHTlr49X0Wa1W3bA2/Y50/63by5JgGwRKtq0nneDS2DF5H5EZj51Z7th+crd2nRE+MpWRVlg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzwxoLdvNc6BAcYIe5tv2kO3zUlKVGtCPRB6ugp05rvU3+qPo2w4y7BDf3hQOrl4xz79g7YrPlWxWuOzY5EPmS99HKX8dkemQbG/Hsg+KDReJFb5FwdKQ6BxQRE2cekhK2qYLyhikT9rW7o/53Hk6Wds4m4p/ZG1rTSW0Apwl8NUqcPZHucHs1XtIbM8eNaQx5UnPD3HNnwg8b6sR8Ajwkl1fOKSM6qKvzOrDFM+Wm2mo8dV0GLRcmjdvY9RJIkBZ3mtzGYR/pA596RyGHlDJ6kwG2sFxcb3zqCAR1aiSRh5DLpd/pUBQedWWkgDZnFwqozTfCxdJ3FyIDUV3f1xp3wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://fbc129ed.ngrok.io/callBack/asynCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://fbc129ed.ngrok.io/callBack/synCallBack";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
