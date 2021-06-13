package cn.cupbread.mims.Config;

import cn.cupbread.mims.Config.SecurityHandler.CustomAccessDeniedHandler;
import cn.cupbread.mims.Config.SecurityHandler.CustomizeAuthenticationEntryPoint;
import cn.cupbread.mims.Config.SecurityHandler.LindTokenAuthenticationFilter;
import cn.cupbread.mims.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private UserService userService;
    @Autowired
    LindTokenAuthenticationFilter lindTokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http相关的配置，包括登入登出、异常处理、会话管理等
//        http.authorizeRequests()
//                .antMatchers("/warehouse/t/page").hasAuthority("query_user")
//                .and()
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .formLogin()
//                .loginPage("/status/401")
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password");
        http.csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/lind-auth/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().headers().cacheControl();
        http.addFilterBefore(lindTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(customAccessDeniedHandler);
        // 禁用缓存
        http.headers().cacheControl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }
}