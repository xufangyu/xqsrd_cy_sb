package com.yemh.xqsrd.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.yemh.xqsrd.account.security.web.authentication.LoginNameRememberServices;
import com.yemh.xqsrd.account.security.web.authentication.XAuthenticationFailureHandler;
import com.yemh.xqsrd.account.service.impl.CustomUserServiceImpl;

/**
 * @author yemh
 * @date 2018/04/14
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码校验处理
     * @return
     */
    @Bean
    UserDetailsService customUserService() {
        return new CustomUserServiceImpl();
    }
    
    /**
     * 记住用户处理
     * @return
     */
    @Bean
    LoginNameRememberServices loginNameRememberServices() {
        return new LoginNameRememberServices("yemh", customUserService());
    }

    /**
     * 登录失败处理
     * @return
     */
    @Bean
    XAuthenticationFailureHandler xAuthenticationFailureHandler() {
        return new XAuthenticationFailureHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 添加csrf忽略
        http.csrf().ignoringAntMatchers(
            "/loginUser",
            "/login",
            "/checkCode",
            "/menu/**",
            "/role/**",
            "/user/**");
        http.authorizeRequests()
            .antMatchers("/lib/**", "/css/**", "/js/**", "/imag/**", "/**/favicon.ico").permitAll()
            // 关闭鉴权
//            .antMatchers("/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                // 设置接收的前端传来的用户名密码字段
//                .usernameParameter(usernameParameter)
                .passwordParameter("password_md5")
            //设置默认登录成功跳转页面
            .defaultSuccessUrl("/")
//            .successForwardUrl("/index")
            .failureHandler(xAuthenticationFailureHandler())
                .permitAll()
            .and()
            //记住登录暂时有bug
            .rememberMe()
                .rememberMeServices(loginNameRememberServices())
                .tokenValiditySeconds(24 * 3600 * 14)
                .key("yemh")
            .and()
            .logout()
                .permitAll()
            .and()
            .headers()
                .frameOptions()
                .sameOrigin();
        http.sessionManagement().
        /**
         * 同一个账号只能在一个地方登陆
         */
        maximumSessions(1);
        /**
         * 自定义session过期策略，替代默认的{@link ConcurrentSessionFilter.ResponseBodySessionInformationExpiredStrategy}，
         * 复写onExpiredSessionDetected方法，默认方法只输出异常，没业务逻辑。这里需要返回json
         */
//        expiredSessionStrategy();
    }

    /**
     * 添加忽略的静态路径
     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/trd/**", "/css/**", "/js/**", "/imag/**", "/**/favicon.ico");
//        super.configure(web);
//    }
    
    

}
