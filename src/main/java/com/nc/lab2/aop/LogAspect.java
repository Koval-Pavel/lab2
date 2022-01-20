package com.nc.lab2.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import static com.nc.lab2.controller.StudentController.log;


@Component
@Aspect
public class LogAspect {


// Testing for Logging
    @Before("execution(* com.nc.lab2.controller.StudentController.*())") // Для всех методов класа студентконтрол
    public void logMethod (JoinPoint joinPoint) {
        System.out.println("Details:" + joinPoint.getSignature().getName());
        log.info("Method " + joinPoint.getSignature().getName() + " started");
        System.out.println("****************************************************");
    }

}
