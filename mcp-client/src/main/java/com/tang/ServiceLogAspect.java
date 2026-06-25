package com.tang;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * ProjectName: SpringAI-MCP-RAG-Dev
 * Package: com.tang
 * ClassName: ServiceLogAspect
 * Author: tmj
 * Date: 2026/6/25 15:02
 * version: 1.0
 * Description:
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    /**
     * TODO:AOP 环绕切面
     *     * 返回任意类型，void，也可以是其他类型参数
     *     com.tang.service.impl 指定的包名，要切的class类的所在包
     *     .. 可以匹配到当前包和子包中的类
     *     * 匹配当前包以及子包下的class类
     *     . 无意义
     *     * 匹配任意方法名
     *     (..) 匹配任意参数列表
     *
     * @param joinPoint
     * @return java.lang.Object
     * @author tmj
     * @since 2026/6/25 15:04
     **/
    @Around("execution(* com.tang.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {

//        long begin = System.currentTimeMillis();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object proceed = joinPoint.proceed();
        String point = joinPoint.getTarget().getClass().getName()
                + "."
                + joinPoint.getSignature().getName();

//        long end = System.currentTimeMillis();
        stopWatch.stop();
//        long takeTime = end - begin;
        long takeTime = stopWatch.getTotalTimeMillis();

        if (takeTime > 3000) {
            log.error("{}.{} 耗时: {}ms", point, takeTime);
        } else if (takeTime > 2000) {
            log.warn("{}.{} 耗时: {}ms", point, takeTime);
        } else {
            log.info("{}.{} 耗时: {}ms", point, takeTime);
        }
        return proceed;
    }
}
