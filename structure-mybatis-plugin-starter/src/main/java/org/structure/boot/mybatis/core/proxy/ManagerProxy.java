package org.structure.boot.mybatis.core.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.structure.boot.mybatis.base.BaseManager;
import org.structure.boot.mybatis.enums.MapperType;
import org.structure.boot.mybatis.configuration.ManagerConfiguration;
import org.structure.boot.mybatis.core.factory.ManagerImplFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @version V1.0.0
 * @Title: ManagerProxy
 * @Package com.structure.boot.manager.core.proxy
 * @Description:
 * @author: lcq
 * @date: 2019/9/11 16:20
 */
public class ManagerProxy implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(ManagerProxy.class);
    private BaseManager manager;
    private String target;
    private Map<String, Method> methodMap;

    public ManagerProxy(String target, String mapperName, MapperType mapperType) {
        this.target = target;
        manager = ManagerImplFactory.baseManager(mapperType);
        manager.setMapperName(mapperName);
        methodMap = new HashMap<>();
        Method[] methods = manager.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            methodMap.put(methods[i].getName(), methods[i]);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        argsFilter(args, method);
        Object invokeResult = null;
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                invokeResult = method.invoke(this, args);
            } else {
                invokeResult = run(method, args);
            }
            Date beginDate = new Date();
            logger.debug("input -> className:{} -> methodName:{} -> args:{}->", this.target,method.getName(),args);
            invokeResult = resultFilter(invokeResult, method);
            Date endDateTime = new Date();
            logger.debug("output -> className:{} -> methodName:{} -> result:{}-> timeDiff:{}", this.target,method.getName(),invokeResult,endDateTime.getTime() - beginDate.getTime());
        } catch (Exception e) {
            logger.error("{}类该方法：{} -> 执行异常：{}", this.target,method.getName(), e.getMessage());
            throw e.getCause();
        }
        return invokeResult;
    }

    /**
     * 核心执行
     *
     * @param method
     * @param args
     * @return
     * @throws Exception
     */
    public Object run(Method method, Object[] args) throws Exception {

        /**
         * 判断动态代理还是使用静态代理
         */
        if (methodMap.containsKey(method.getName())) {
            //判断是否需要从新赋值 mapper
            if (null == manager.getMapper()) {
                Object mapper = ManagerConfiguration.getMapperMap().get(manager.getMapperName());
                if (null == mapper) {
                    throw new RuntimeException("THIS IS MAPPER " + manager.getMapperName() + "IS NOT FOUND");
                }
                manager.setMapper(mapper);
            }
            return methodMap.get(method.getName()).invoke(manager, args);
        } else {
            return ManagerStaticProxy.invoke(target, method, args);
        }
    }

    /**
     * 入参拦截
     *
     * @param args
     * @param method
     */
    public void argsFilter(Object[] args, Method method) {
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                if (null == args[i]) {
                    //抛出异常入参为空
                    logger.error("该方法：{}第{}个参数为 NULL", method.getName(), i);
                }
            }
        }
    }

    /**
     * 出参拦截
     *
     * @param invokeResult
     * @param method
     * @return
     */
    public Object resultFilter(Object invokeResult, Method method) {
        if (invokeResult instanceof List && null == invokeResult) {
            invokeResult = new ArrayList<>();
        }
        if (invokeResult instanceof Integer) {
            if ((int) invokeResult <= 0 && !method.getName().toLowerCase().contains("count")) {
                logger.error("{} -> 执行的条数为 {} ", method.getName(), invokeResult);
                //todo 抛出异常执行未能成功
            }
        }
        return invokeResult;
    }

}
