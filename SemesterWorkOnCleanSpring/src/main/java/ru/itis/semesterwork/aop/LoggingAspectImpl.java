package ru.itis.semesterwork.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class LoggingAspectImpl implements LoggingAspect {

    @Pointcut("execution(* ru.itis.semesterwork.services.*.*(..))")
    public void logMethod(){}

    @SneakyThrows
    @Override
    @Around("logMethod()")
    public Object logMethodAround(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("Passed parameters: " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object object = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("Method end");
        return object;
    }

    @Override
    //execution happens before any {public} method,
    // which return anything and has any amount of parameters
    //in package ru.itis.semesterwork.services
    @Before("logMethod()")
    public void logMethodBefore(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature() + " has started");
    }
}
