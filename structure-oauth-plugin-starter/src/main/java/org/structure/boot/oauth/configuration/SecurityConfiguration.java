package org.structure.boot.oauth.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: CHUCK
 * @date: 2020/11/13 15:52
 * @description: WebSecurityConfigurerAdapter适配器
 */
@Slf4j
@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 拦截所有请求,使用httpBasic方式登陆
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("HttpSecurity");
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint((req,resp, authException) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token","/login","/feign/**").permitAll()
                .antMatchers("/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint())
                .and()
                .httpBasic();
    }

    /**
     *  核心过滤器配置方法
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略以下路径内容
        web.ignoring().antMatchers("/js/**", "/css/**", "/image/**");
    }

    /**
     * 授权中心管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("authenticationManagerBean");
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /**
     * 设置密码比对规则
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
