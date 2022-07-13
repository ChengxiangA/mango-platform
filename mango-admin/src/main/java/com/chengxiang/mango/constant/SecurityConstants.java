package com.chengxiang.mango.constant;

public final class SecurityConstants {
    public static final String[] STATIC_RESOURCE_WHITELISTS = {"/js/**","/css/**","/index.html","/img/**","/fonts/**","/favicon.ico"};

    public static final String[] SWAGGER_WHITELISTS = {"/v2/**","/swagger-ui.html/**","/swagger-resource/**","/webjars/**"};

    public static final String CAPTCHA_WHITELISTS = "/captcha.jpg";

    public static final String ACTUATOR_WHITELISTS = "/actuator/**";

    public static final String DRUID_WHITELISTS = "/druid/**";
}
