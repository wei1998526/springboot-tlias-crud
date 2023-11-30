package com.wei.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MyAspect1 {
    /**
     * 切入点表达式
     */
    @Pointcut("execution(* com.wei.service.impl.DeptServiceImpl.*(..))")
    private void pt(){}

    /**
     * before前置通知，目标方法运行前执行
     */
    @Before("pt()")
    public void before(){
        log.info("before前置通知.....");
    }

    /**
     * around环绕通知，目标方法运行前，运行后都执行
     * @param joinPoint 连接点，调用目标对象的原始方法
     * @return
     * @throws Throwable
     */
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around环绕通知-开始.....");
        //调用目标对象的原始方法
        Object result = joinPoint.proceed();
        log.info("around环绕通知-结束.....");
        return result;
    }

    /**
     * after后置通知，目标方法运行后执行
     */
    @After("pt()")
    public void after(){
        log.info("after后置通知.....");
    }

    /**
     * afterReturning原始方法运行结束，并正常返回后执行
     */
    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning原始方法运行结束，并正常返回.....");
    }

    /**
     * 目标方法运行时出现异常时执行
     */
    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("afterThrowing目标方法运行时出现异常......");
    }
}
