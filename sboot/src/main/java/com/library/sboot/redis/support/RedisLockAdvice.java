package com.library.sboot.redis.support;

import jodd.util.ThreadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @ClassName: RedisLockAdvice
 * @Description: 分布式锁注解实现
 * @author feifei.liu
 * @date 2018/12/27 11:06
 */
@Component
@Aspect
public class RedisLockAdvice {
    private static final Logger log = LoggerFactory.getLogger(RedisLockAdvice.class);

    @Resource
    private RedisDistributionLock redisDistributionLock;

    @Around("@annotation(RedisLockAnnoation)")
    public Object processAround(ProceedingJoinPoint point) throws Throwable {
        //获取方法上的注解对象
        String methodName = point.getSignature().getName();
        Class<?> classTarget = point.getTarget().getClass();
        Class<?>[] parameterTypes = ((MethodSignature)point.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(methodName, parameterTypes);
        RedisLockAnnoation redisLockAnnoation = method.getDeclaredAnnotation(RedisLockAnnoation.class);

        //拼装分布式锁的key
        String[] keys = redisLockAnnoation.keys();
        Object[] args = point.getArgs();
        Object arg = args[0];
        StringBuilder sb = new StringBuilder();
        sb.append(redisLockAnnoation.keyPrefix());
        for (String key: keys) {
            String getMethod = "get"+ StringUtils.capitalize(key);
            sb.append(MethodUtils.invokeExactMethod(arg, getMethod)).append("_");
        }
        String redisKey = StringUtils.removeEnd(sb.toString(), "_");

        //执行分布式锁的逻辑
        if (redisLockAnnoation.isSpin()) {
            //阻塞锁
            int lockRetryTime = 0;
            try {
                while (!redisDistributionLock.lock(redisKey, redisLockAnnoation.expireTime())) {
                    if (lockRetryTime++ > redisLockAnnoation.retryTime()) {
                        log.error("redis lock exception. key[{}], lockRetryTime:[{}]", redisKey, lockRetryTime);
//                        ExceptionUtils.
//                        throw ExceptionUtil.geneException(CommonExceptionEnum.SYSTEM_ERROR);
                        throw new Exception("error");
                    }
//                    ThreadUtil.holdXms(redisLockAnnoation.waitTime());
                    ThreadUtil.sleep(redisLockAnnoation.waitTime());
                }
                return point.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        } else {
            //非阻塞锁
            try {
                if (!redisDistributionLock.lock(redisKey)) {
                    log.error("redis lock exception. key:[{}]", redisKey);
//                    throw ExceptionUtil.geneException(CommonExceptionEnum.SYSTEM_ERROR);
                    throw new Exception("error");
                }
                return point.proceed();
            } finally {
                redisDistributionLock.unlock(redisKey);
            }
        }
    }
}
