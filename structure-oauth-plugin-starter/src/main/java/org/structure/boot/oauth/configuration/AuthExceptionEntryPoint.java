package org.structure.boot.oauth.configuration;

import cn.hutool.http.ContentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.structure.boot.common.entity.ResultVO;
import org.structure.boot.common.exception.CommonException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date:  2018/8/2
 * Time:  19:38
 * 登录异常捕获处理
 *
 * @author Administrator
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException {
        ResultVO exception = null;
        if (authException.getCause() instanceof CommonException) {
            CommonException commonException = (CommonException) authException.getCause();
            exception = ResultVO.fail(commonException.getCode(), commonException.getMsg());
        } else {
            exception = ResultVO.exception(authException.getMessage());
        }
        response.setContentType(ContentType.JSON.toString());
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), exception);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}