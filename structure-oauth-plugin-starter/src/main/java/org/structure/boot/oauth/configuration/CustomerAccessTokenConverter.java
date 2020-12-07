package org.structure.boot.oauth.configuration;

import org.structure.boot.oauth.pojo.po.OauthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.structure.boot.common.constant.AuthConstant;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 加入自定义用户属性到token
 *
 * @author Administrator
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {

    public CustomerAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }

    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            LinkedHashMap response = new LinkedHashMap();
            response.put(AuthConstant.USERNAME, authentication.getName());
            //设置token中的数据
            OauthUser oauthUser = (OauthUser) authentication.getPrincipal();
            response.put(AuthConstant.USER_ID, oauthUser.getId());
            return response;
        }
    }
}
