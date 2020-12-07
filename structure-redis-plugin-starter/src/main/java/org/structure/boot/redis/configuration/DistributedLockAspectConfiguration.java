package org.structure.boot.redis.configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;
import org.structure.boot.redis.annotation.RedisLock;
import org.structure.boot.redis.lock.DistributedLock;

@Aspect
@Configuration
public class DistributedLockAspectConfiguration {

    private final Logger logger = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);

    @Autowired
    private DistributedLock distributedLock;

    @Pointcut("@annotation(org.structure.boot.redis.annotation.RedisLock)")
    private void lockPoint() {

    }

    public static String getValueBySpelKey(String key, String[] parameterNames, Object[] values){
        //不存在表达式返回
        if (!key.contains("#")) {
            return key;
        }
        //使用下划线拆分表达式
        String[] spelKeys = key.split("_");
        //要返回的key
        StringBuilder sb = new StringBuilder();
        //遍历拆分结果用解析器解析
        for (int i = 0 ; i <= spelKeys.length - 1 ; i++) {
            if (!spelKeys[i].startsWith("#")) {
                sb.append(spelKeys[i]);
                continue;
            }
            String tempKey = spelKeys[i];
            //spel解析器
            ExpressionParser parser = new SpelExpressionParser();
            //spel上下文
            EvaluationContext context = new StandardEvaluationContext();
            for (int j = 0; j < parameterNames.length; j++) {
                context.setVariable(parameterNames[j], values[j]);
            }
            Expression expression = parser.parseExpression(tempKey);
            Object value = expression.getValue(context);
            if (value != null) {
                sb.append(value.toString());
            }
        }
        //返回
        return sb.toString();
    }

    @Around("lockPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        //获取参数名
        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(((MethodSignature) pjp.getSignature()).getMethod());
        //获取参数值
        Object[] args = pjp.getArgs();
        String key = getValueBySpelKey(redisLock.value(),parameterNames,args);
        int retryTimes = redisLock.action().equals(RedisLock.LockFailAction.CONTINUE) ? redisLock.retryTimes() : 0;
        boolean lock = distributedLock.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
        if (!lock) {
            logger.debug("get lock failed : " + key);
            return null;
        }

        //得到锁,执行方法，释放锁
        logger.debug("get lock success : " + key);
        try {
            return pjp.proceed();
        } catch (Exception e) {
            logger.error("execute locked method occured an exception", e);
        } finally {
            boolean releaseResult = distributedLock.releaseLock(key);
            logger.debug("release lock : " + key + (releaseResult ? " success" : " failed"));
        }
        return null;
    }
}