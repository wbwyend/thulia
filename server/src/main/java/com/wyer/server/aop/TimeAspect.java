package com.wyer.server.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Function:
 * Writer: wyer
 * Date: 2024/02/08 15:51
 **/
@Component
@Aspect
public class TimeAspect {

    @Autowired
    private HttpServletRequest request;

    @Around("execution(* com.wyer.server.controller.WebController.get(..))")
    public Object readTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        System.out.println(request.getMethod());

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(proceedingJoinPoint.getSignature() + " " + (double)(end - begin) / 1000.0 );

        return result;
    }

}
