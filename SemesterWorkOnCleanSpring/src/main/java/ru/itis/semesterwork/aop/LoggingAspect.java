package ru.itis.semesterwork.aop;

import org.aspectj.lang.JoinPoint;

public interface LoggingAspect {

    void logMethodAfter(JoinPoint joinPoint);

    void logMethodBefore(JoinPoint joinPoint);
}
