package org.structure.boot.resource.configuration;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.structure.boot.common.entity.ResultVO;
import org.structure.boot.common.enums.ExceptionRsType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *     chuck
 *     2019/11/28 11:36
 * </p>
 * @author chuck
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(Header.CONTENT_TYPE.toString(), ContentType.JSON.toString());
        try {
            if(cause instanceof InvalidTokenException) {
                //坏的token或过期token
                ResultVO result = ResultVO.fail(ExceptionRsType.INVALID_AUTHENTICATION.getCode(),authException.getMessage());
                response.getWriter().write(JSONObject.toJSONString(result));
            }else{
                //用户未登录
                ResultVO result = ResultVO.fail(ExceptionRsType.NOT_LOGGED_IN.getCode(), authException.getMessage());
                response.getWriter().write(JSONObject.toJSONString(result));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}