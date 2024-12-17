package com.example.crudwarehouse.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Component
@Slf4j
public class TransactionTimingAspect {
    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object measureTransactionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getMethod().getDeclaringClass().getSimpleName();
        String method = methodSignature.getMethod().getName();

        ThreadLocal<Long> startTime = new ThreadLocal<>();
        startTime.set(System.currentTimeMillis());

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            public void afterCommit() {
                long executionTime = System.currentTimeMillis() - startTime.get();
                log.info(String.format("%s.%s transaction execution time: %s ms", className, method, executionTime));
            }
        });

        return joinPoint.proceed();
    }
}
