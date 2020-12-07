package org.structure.boot.resource.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author CHUCK
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = {ResourceServerConfig.class})
public class SecurityAutoConfiguration {

    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private SecurityProperties properties;

    @Bean
    @Qualifier("tokenStore")
    @ConditionalOnMissingBean(JwtTokenStore.class)
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    @ConditionalOnMissingBean(JwtAccessTokenConverter.class)
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource(properties.getPublicCert());
        String publicKey ;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        //读取自定义
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }

    @Bean
    public AuthExceptionEntryPoint authExceptionEntryPoint(){
        return new AuthExceptionEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}
