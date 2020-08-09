package com.xtm.test1.aop;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * redis分布式锁的切面
 */
@Aspect
@Component
@Slf4j
public class RedisLockAspect {
    private WebApplicationContext webApplicationContext;

    public RedisLockAspect(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Pointcut("@annotation(com.xtm.test1.aop.RedisLock)")
    private void apiAop(){

    }

    @Around("apiAop()")
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        String localRegistry = redisLock.value();
        if(StringUtils.isBlank(localRegistry)){
            throw new RuntimeException("获取 Registry beann 失败");
        }

        RedisLockRegistry redisLockRegistry = (RedisLockRegistry) webApplicationContext.getBean(redisLock.value());

        Lock lock = redisLockRegistry.obtain(signature.getName());
        boolean b = false;
        for(int i =0 ; i<3;i++){
            b = lock.tryLock(redisLock.time(), TimeUnit.SECONDS);
            if(b){
                break;
            }else {
                continue;
            }
        }
        log.info("获取锁====="+b);
        Object proceed = null;
        try{
            proceed = point.proceed();
        }catch (Exception e){
            throw e;
        }finally {
            try{
                lock.unlock();
            }catch (Exception e){
                log.error(e.getMessage(),e);
            }
        }


        return proceed;

    }
}
