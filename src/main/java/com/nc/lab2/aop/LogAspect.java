package com.nc.lab2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.nc.lab2.Lab2Application.log;

@Component
@Aspect
public class LogAspect {


    @Before("execution(* com.nc.lab2.*.*.*())")
    public void logMethod (JoinPoint joinPoint) {
        log.info("LOG.INFO FROM LOG ASPECT Method " + joinPoint.getSignature().getName() + " started");
    }

}
