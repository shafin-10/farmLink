package com.example.FarmLink.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.example.FarmLink.demo..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        Object returnObj = new Object();
        log.info(joinPoint.getSignature().toString() +  " method execution start");
        Instant start = Instant.now();
        returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Time took to execcute " + timeElapsed);
        log.info(joinPoint.getSignature().toString() + " method execution end");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.eazybytes.FarmLink.demo..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception exception){
        log.error(joinPoint.getSignature().toString() + " an exception happened due to : " +
                exception.getMessage());
    }
}
