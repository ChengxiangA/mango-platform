package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysUser;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.security.JwtAuthenticationToken;
import com.chengxiang.mango.service.SysLoginLogService;
import com.chengxiang.mango.service.SysUserService;
import com.chengxiang.mango.util.IPUtil;
import com.chengxiang.mango.util.PasswordEncoder;
import com.chengxiang.mango.util.SecurityUtil;
import com.chengxiang.mango.vo.LoginBean;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@Api(tags = {"登录接口"})
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        out.close();
    }

    @PostMapping("/login")
    public HttpResult login(@RequestBody LoginBean loginBean,HttpServletRequest request) {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        Object rightCaptcha =  request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(rightCaptcha == null) {
            return HttpResult.error("验证码已失效");
        }
        if(!captcha.equals(rightCaptcha)) {
            return HttpResult.error("验证码不正确");
        }
        SysUser user = sysUserService.findByName(username);
        if(user == null) {
            return HttpResult.error("账号不存在");
        }
        PasswordEncoder passwordEncoder = new PasswordEncoder(user.getSalt());
        if(!passwordEncoder.match(password,user.getPassword())) {
            return HttpResult.error("密码错误");
        }
        if(user.getStatus() == 0) {
            return HttpResult.error("账号已被锁定,请联系管理员");
        }
        JwtAuthenticationToken token = SecurityUtil.login(request, username, user.getPassword(), authenticationManager);
        sysLoginLogService.writeLoginLog(username, IPUtil.getIpAddress(request));
        return HttpResult.ok("成功登录",token);
    }
}