package com.wei.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect //Aop类

/**
 * 计算方法执行耗时
 */
public class TimeAspect {
    //Around环绕通知，方法的作用范围
    @Around("execution(* com.wei.service.*.*(..))")  //切入点表达式
    public Object recordTime(ProceedingJoinPoint JoinPoint) throws Throwable {
        //1.获取方法运行开始时间
        long begin = System.currentTimeMillis();

        //2.调用原始方法运行
        Object result = JoinPoint.proceed();

        //3.记录方法的结束时间，计算方法耗时
        long end = System.currentTimeMillis();
        long time = end - begin;
        log.info(JoinPoint.getSignature() + "方法执行耗时：{} ms", time);

        return result;
    }
}
