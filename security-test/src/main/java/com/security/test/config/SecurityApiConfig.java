package com.security.test.config;

import com.security.test.filter.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description 对于登录接口，登录成功后的响应，登录失败后的响应，
 * 我们都可以在 WebSecurityConfigurerAdapter 的实现类中进行配置。
 * @date created on 2020/5/9
 */
@Configuration
public class SecurityApiConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    VerifyCodeFilter verifyCodeFilter;


    /**
     * 如果某一个请求地址不需要拦截的话，有两种方式实现：
     *
     * 设置该地址匿名访问
     * 直接过滤掉该地址，即该地址不走 Spring Security 过滤器链
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/verify_code");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http)

        //和表单登录相关的接口统统都直接通过

        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        //开启登录配置
        //表示访问 /hello 这个接口，需要具备 admin 这个角色
        //表示剩余的其他接口，登录之后就能访问
        //定义登录页面，未登录时，访问一个需要登录之后才能访问的接口，会自动跳转到该页面
        //登录处理接口
        //定义登录时，用户名的 key，默认为 username
        //定义登录时，用户密码的 key，默认为 password
        http
                .authorizeRequests()
                .antMatchers("/hello").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login_p")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("uname")
                .passwordParameter("passwd")
                //登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("success");
                        out.flush();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("fail");
                        out.flush();
                    }
                })
                 //和表单登录相关的接口统统都直接通过
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.write("logout success");
                        out.flush();
                    }
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
