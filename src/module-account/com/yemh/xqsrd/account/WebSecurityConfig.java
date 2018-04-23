package com.yemh.xqsrd.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.yemh.xqsrd.account.service.impl.CustomUserService;

/**
 * @author yemh
 * @date 2018/04/14
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/checkCode");
        http.authorizeRequests()
//            .antMatchers("/plugins/**", "/css/**", "/js/**", "/imag/**", "/**/favicon.ico").permitAll()
            // 关闭鉴权
            .antMatchers("/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            //设置默认登录成功跳转页面
            .defaultSuccessUrl("/index")
//            .successForwardUrl("/index")
            .failureUrl("/login?error")
                .permitAll()
            .and()
            //记住登录暂时有bug
//            .rememberMe()
//                .tokenValiditySeconds(24 * 3600 * 14)
//                .key("yemh")
//            .and()
            .logout()
                .permitAll()
            .and()
            .headers()
                .frameOptions()
                .sameOrigin();
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
