package com.xzit.rental.security;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Resource
    private LoginSucdessHandler loginSucdessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private VerifyTokenFilter verifyTokenFilter;

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http)
             throws Exception{
         // 登录前过滤配置
         http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);


         http.formLogin()
                 .loginProcessingUrl("/rental/user/login") // 设置登录处理URL
                 .successHandler(loginSucdessHandler) // 设置登录成功处理器
                 .failureHandler(loginFailHandler) // 设置登录失败处理器
                 .and()
                 .sessionManagement()
                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置会话创建策略为无状态
                 .and()
                 .authorizeHttpRequests() // 授权请求配置
                 .requestMatchers("/rental/user/login") // 匹配登录请求
                 .permitAll() // 允许所有请求访问
                 .anyRequest().authenticated() // 任何其他请求需要认证
                 .and()
                 .exceptionHandling() // 异常处理配置
                 .authenticationEntryPoint(customerAnonymousEntryPoint) // 设置未认证入口点
                 .accessDeniedHandler(customerAccessDeniedHandler) // 设置访问拒绝处理器
                 .and()
                 .cors() // 跨域配置
                 .and()
                 .csrf().disable() // 关闭CSRF保护 跨站请求伪造 是一种网络攻击
                 .userDetailsService(customerUserDetailsService); // 设置用户详情服务
         return http.build(); // 构建并返回安全过滤链
     }
}
