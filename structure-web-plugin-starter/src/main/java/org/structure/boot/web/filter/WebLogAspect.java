package org.structure.boot.web.filter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.structure.boot.common.entity.ControllerLog;
import org.structure.boot.common.enums.LogEnums;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author lcq
 */
public class WebLogAspect {

    private Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    private long c;

    private ControllerLog controllerLog;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        controllerLog = new ControllerLog();
        controllerLog.setMethod(request.getMethod());
        controllerLog.setIpAddress(request.getRemoteAddr());
        controllerLog.setUrl(request.getRequestURL().toString());
        controllerLog.setTargetMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        controllerLog.setArgs(Arrays.toString(joinPoint.getArgs()));
        controllerLog.setBeginTime(sdf.format(new Date()));
    }

    /**
     * 后处理  returning的值和doAfterReturning的参数名一致
     *
     * @param ret
     * @throws Throwable
     */
    public void doAfterReturning(Object ret) {
        controllerLog.setTimeDiff(c);
        controllerLog.setType(LogEnums.CONTROLLER);
        controllerLog.setEndTime(sdf.format(new Date()));
        // 处理完请求，返回内容
        log.info(controllerLog.toJSONString());
    }

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable  {
        long startTime = System.currentTimeMillis();
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        c = System.currentTimeMillis() - startTime;
        return ob;
    }

}
