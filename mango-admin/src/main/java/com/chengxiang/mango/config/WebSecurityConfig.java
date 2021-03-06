package com.chengxiang.mango.config;

import com.chengxiang.mango.constant.SecurityConstants;
import com.chengxiang.mango.security.JwtAuthenticationFilter;
import com.chengxiang.mango.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(SecurityConstants.STATIC_RESOURCE_WHITELISTS).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(SecurityConstants.SWAGGER_WHITELISTS).permitAll()
                .antMatchers(SecurityConstants.CAPTCHA_WHITELISTS).permitAll()
                .antMatchers(SecurityConstants.ACTUATOR_WHITELISTS).permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .antMatchers("/user/**").permitAll() // 测试
                .antMatchers("/log/**").permitAll() // 测试
                .antMatchers("/loginlog/**").permitAll() // 测试
                .anyRequest().permitAll().and() // 为了测试，放开所有控制器
                .formLogin();
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
