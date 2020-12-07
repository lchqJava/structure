package org.structure.boot.resource.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.structure.boot.common.constant.AuthConstant;
import org.structure.boot.common.constant.SymbolConstant;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 *     chuck
 *     2019/11/28 11:36
 * </p>
 * @author chuck
 */
public class CustomerAccessTokenConverter extends DefaultAccessTokenConverter {


    public CustomerAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }


    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Authentication extractAuthentication(Map<String, ?> map) {
            Collection authorities = this.getAuthorities(map);
            return new UsernamePasswordAuthenticationToken(map, SymbolConstant.N_A, authorities);
        }

        private Collection<? extends GrantedAuthority> getAuthorities(Map<String, ?> map) {
            if (!map.containsKey(AuthConstant.AUTHORITIES)) {
                //参数不包含任何权限存入1长度的权限数组标识单纯的普通用户
                return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.arrayToCommaDelimitedString(new String[]{"ONLY_USER"}));
            } else {
                Object authorities = map.get(AuthConstant.AUTHORITIES);
                if (authorities instanceof String) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList((String) authorities);
                } else if (authorities instanceof Collection) {
                    return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection) authorities));
                } else {
                    throw new IllegalArgumentException("Authorities must be either a String or a Collection");
                }
            }
        }

    }

}