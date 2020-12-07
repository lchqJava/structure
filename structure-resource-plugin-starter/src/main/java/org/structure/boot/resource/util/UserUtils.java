package org.structure.boot.resource.util;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;

import static org.structure.boot.common.constant.AuthConstant.*;

/**
 * @author CHUCK
 */
public class UserUtils {

    private static Map hasToken(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if(principal instanceof Map){
            return (Map) principal;
        }
        return null;
    }

    /**
     * 获取当前token的用户名
     * @return
     */
    public static String getCurrentAuthenticationUsername(){
        Map map = hasToken();
        if(map != null){
            String userName = (String) map.get(USERNAME);
            return userName;
        }
        return null;
    }

    /**
     * 获取当前token的用户id
     * @return
     */
    public static String getCurrentAuthenticationUserId(){
        Map map = hasToken();
        if(map != null){
            String userId = (String) map.get(USER_ID);
            return userId;
        }
        return null;
    }

    /**
     * 获取权限
     * @return
     */
    public static List<SimpleGrantedAuthority> getCurrentAuthorities(){
        return (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

    /**
     * 获取unionId
     * @return
     */
    public static Object getTokenValue(String name){
        Map map = hasToken();
        if(map != null){
            return map.get(name);
        }
        return null;
    }

    /**
     * 获取设置密码状态
     * @return
     */
    public static Boolean getCurrentAuthenticationNoPwdSet(){
        Map map = hasToken();
        if(map != null){
            Boolean noPasswordSet = (Boolean) map.get(NO_PASSWORD_SET);
            return noPasswordSet;
        }
        return null;
    }
}
