package com.xzit.rental.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.xzit.rental.utils.JwtUtils;
import com.xzit.rental.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


/**
 * OncePerRequestFilter是spring boot 提供的过滤器抽象类
 * 在Spring security应用广泛，可以用来过滤请求
 * 这个过滤器通常被 用于继承实现并在每次请求时执行
 */

@Component
public class VerifyTokenFilter extends OncePerRequestFilter {
    @Value("${request.login-url}")
    private String loginUrl;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (!StrUtil.equals(url,loginUrl)){
            try{
                validateToken(request, response);
            }catch (AuthenticationException e){
                loginFailHandler.onAuthenticationFailure(request,response,e);
            }
        }
        doFilter(request,response,filterChain);
    }
    private void validateToken(HttpServletRequest request,
                               HttpServletResponse response) throws AuthenticationException {
        //校验token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            token = request.getParameter("token");
        }
        if (StrUtil.isEmpty(token)){
            throw new CustomerAuthenticationException("token为空");
        }

        String tokenKey = "token:"+token;
        String tokenValue = redisUtils.get(tokenKey);
        if (StrUtil.isEmpty(tokenValue)){
            throw new CustomerAuthenticationException("token已过期");
        }
        if (!StrUtil.equals(token,tokenValue)){
            throw new CustomerAuthenticationException("token错误");
        }
        String username = JwtUtils.parseToken(token)
                .getClaim("username").toString();
        if (StrUtil.isEmpty(username)){
            throw new CustomerAuthenticationException("token解析失败");
        }
        UserDetails userDetails = customerUserDetailsService
                .loadUserByUsername(username);
        if (userDetails == null){
            throw new CustomerAuthenticationException("用户不存在");
        }
        // 创建认证信息，并设置到SecurityContextHolder中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }
}
