package ru.itis.semesterwork.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspectImpl implements LoggingAspect {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Pointcut("execution(public * ru.itis.semesterwork.*.*(..))")
    public void logMethod(){}

    @Override
    @AfterReturning("logMethod()")
    public void logMethodAfter(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " has finished");
    }

    @Override
    //execution happens before any {public} method,
    // which return anything and has any amount of parameters
    @Before("logMethod()")
    public void logMethodBefore(JoinPoint joinPoint) {
        logger.info(joinPoint.getSignature() + " has started");
    }
}
