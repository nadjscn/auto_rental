package com.xzit.rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xzit.rental.entity.User;
import com.xzit.rental.utils.JwtUtils;
import com.xzit.rental.utils.RedisUtils;
import com.xzit.rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import
        org.springframework.security.web.authentication.AuthenticationSuccessHandler
        ;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSucdessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisUtils redisUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        //设置客户端的响应的内容类型是json
        response.setContentType("application/json;charset=utf-8");
        User user = (User)authentication.getPrincipal();
        //生成token的处理
        Map<String,Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        String token = JwtUtils.createToken(map);
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        long expireTime = Convert.toDate(claim).getTime();
        AuthenticationResult authenticationResult = new AuthenticationResult(user.getId(),
                ResultCode.SUCCESS,token,expireTime);
        //获取一个结果
        String result= JSON.toJSONString(authenticationResult,
                SerializerFeature.DisableCircularReferenceDetect);
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        // 将token存入Redis，设置过期时间
        String tokenKey="token:"+token;
        long nowTime= DateTime.now().getTime();
        redisUtils.set(tokenKey,token,(expireTime-nowTime)/1000);
    }
}
