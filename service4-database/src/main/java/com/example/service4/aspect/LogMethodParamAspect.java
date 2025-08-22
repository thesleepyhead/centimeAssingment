package com.example.service4.aspect;

import com.example.service4.annotation.LogMethodParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogMethodParamAspect {
    
    private static final Logger log = LoggerFactory.getLogger(LogMethodParamAspect.class);
    
    @Before("@annotation(logMethodParam)")
    public void logMethodParameters(JoinPoint joinPoint, LogMethodParam logMethodParam) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        log.info("Method {}.{} called with parameters: {}", 
                className, methodName, Arrays.toString(args));
    }
}
