package com.dzb.springcloud.aspect;

import com.dzb.springcloud.Utils.IpUtil;
import com.dzb.springcloud.annotation.SysoLog;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.util.resources.ga.LocaleNames_ga;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @author Ding ZhenBei
 * @data 2020/3/12 18:19
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    /**
     * 定义日志切点
     */
    @Pointcut("@annotation(com.dzb.springcloud.annotation.SysoLog)")
    public void logPointCut(){
    }


    /**
     * 环绕通知
     * @param point
     * @return
     */

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object proceed = point.proceed();
        long time = System.currentTimeMillis() - startTime;
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获取注解内容
        SysoLog annotation = method.getAnnotation(SysoLog.class);
        if (annotation != null) {
            log.info(annotation.value()+"总耗时为={}",time+"毫秒");
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("当前接口"+request.getRequestURL()+"请求ip为"+ IpUtil.getIpAddr(request));
        return proceed;
     }
}
