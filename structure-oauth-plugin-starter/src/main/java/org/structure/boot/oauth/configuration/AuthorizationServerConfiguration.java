package org.structure.boot.oauth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: CHUCK
 * @date: 2020/11/13 15:46
 * @description: 配置授权中心信息
 */
@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.structure.boot.oauth.logic.**")
@MapperScan(basePackages = "org.structure.boot.oauth.repository.mapper.**")
@ConditionalOnClass(OauthProperties.class)
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private DataSource dataSource;

    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private OauthProperties oauthProperties;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //设置加密
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(oauthProperties.getJksPath()), oauthProperties.getPassword().toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(oauthProperties.getKeyPair()));
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter());
        return converter;
    }

    /**
     * 配置JdbcClient
     * @return
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService() {
        // 初始化终端信息JDBC查询方法
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        // 设置条件查询和默认查询的SQL语句
        return clientDetailsService;
    }
    /**
     * 保存token和用户信息
     * @return
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 指定token存储位置和指定认证管理器
        endpoints
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        //拿到增强器链
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancerChain.setTokenEnhancers(enhancers);
        endpoints.tokenEnhancer(enhancerChain).authenticationManager(authenticationManager);
        enhancers.add(jwtAccessTokenConverter);
        endpoints.tokenStore(tokenStore);
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.passwordEncoder(new BCryptPasswordEncoder());
    }
}
