package com.example.restservice.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class ProfilingAspect {

    @Around(value = "@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();

        Object proceed = joinPoint.proceed();

        Duration duration = Duration.between(start, Instant.now());

        System.out.println(joinPoint.getSignature() + " executed in " + duration);
        return proceed;
    }
}
