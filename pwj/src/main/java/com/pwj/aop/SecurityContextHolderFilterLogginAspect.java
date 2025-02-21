package com.pwj.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityContextHolderFilterLogginAspect {
    private static final Logger logger = LoggerFactory.getLogger(SecurityContextHolderFilterLogginAspect.class);

    @Before("execution(* org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(..))")
    public void logSecurityContextFilter2(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        
        // 로그 추가 (slf4j 사용)
        logger.trace("세션 접근 시도 확인, 요청주소 : {}", request.getRequestURI());
        System.out.println("doFilter 들어옴");
        System.out.println(joinPoint.getThis());
    }
    @After("execution(* org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(..))")
    public void logSecurityContextFilter(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        
        // 로그 추가 (slf4j 사용)
        logger.trace("세션 접근 시도 확인, 요청주소 : {}", request.getRequestURI());
        System.out.println("doFilter 들어옴2");
        System.out.println(joinPoint.getThis());
        // 필터 실행
    }
    
    @Before("execution(* org.springframework.security.web.context.SecurityContextRepository.loadDeferredContext(..))")
    public void logSecurityContextFsilter(JoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        
        // 로그 추가 (slf4j 사용)
        logger.trace("세션 접근 시도 확인, 요청주소 2: {}", request.getRequestURI());
        
        System.out.println("load메서드 들어옴");

        // 필터 실행
    }
}
