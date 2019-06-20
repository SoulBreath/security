package com.spring.security.config;

import com.spring.security.security.MyFilterSecurityInterceptor;
import com.spring.security.security.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author xuyang
 * @version 1.0
 * @date 2019/6/19 12:47
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    /**
     * 注册UserDetailsService 的bean
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() { //覆盖写userDetailsService方法 (1)
        return new MyUserDetailService();

    }

    /**
     * user Details Service验证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置资源访问权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()          // 其余所有资源都要经过验证
                .and().formLogin().loginPage("/login").permitAll() // 登陆URL请求路径
                .defaultSuccessUrl("/index").permitAll()    // 登陆成功跳转路径
                .and().logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login").invalidateHttpSession(true); // 注销及跳转页面

        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     * 默认不拦截的静态资源
     * @param web
     * @throws Exception
     */
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }


}