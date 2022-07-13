package com.chengxiang.mango.config;

import com.chengxiang.mango.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//    }

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
                .anyRequest().authenticated().and()
                .formLogin().and().httpBasic();
    }
}
