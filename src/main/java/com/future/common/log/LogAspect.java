package com.future.common.log;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAsync
public class LogAspect {
    
    @Pointcut("@annotation(com.future.common.log.Log)")
    public void logPointcut() {
    }

    @AfterReturning(pointcut = "logPointcut()")
    public void doBefore(JoinPoint joinPoint) {

    }

    @Async
    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        Log log = getAnnotation(joinPoint);
        if(log == null) {
            return;
        }
        // 获取当前用户
        
    }

    private Log getAnnotation(final JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if(method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

}
