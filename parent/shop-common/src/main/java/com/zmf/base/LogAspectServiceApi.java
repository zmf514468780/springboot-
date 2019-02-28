package com.zmf.base;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @Auther: zmf
 * @Date: 2019-01-15 22:57
 * @Description:  日志横切api ，用于在请求时输入日志。
 */
@Aspect
@Component
@Slf4j
public class LogAspectServiceApi {
    private JSONObject jsonObject = new JSONObject();

    @Pointcut("execution(public * com.zmf.service.*.*(..))")
    private void controllerAspect() {

    }

    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=========请求内容=================");
        try {
            log.info("请求地址：" + request.getRequestURL().toString());
            log.info("请求方式：" + request.getMethod());
            log.info("请求方法：" + joinPoint.getSignature());
            log.info("请求方法参数：" + Arrays.toString(joinPoint.getArgs()));

        } catch (Exception e) {
            log.error("###LogAspectServiceApi.class methodBefore() ### ERROR:", e);

        }
        log.info("=========请求内容=================");
        ;
    }

    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturning(Object o) {
        log.info("----------返回内容------------");
        try {
            log.info("Response内容:" + jsonObject.toJSONString(o));

        } catch (Exception e) {
            log.error("###LogAspectServiceApi.class methodAfterReturing() ### ERROR:", e);
        }
        log.info("----------返回内容------------");
    }

}
