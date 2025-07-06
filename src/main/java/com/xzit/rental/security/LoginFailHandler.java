package com.xzit.rental.security;

import com.alibaba.fastjson.JSON;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();

        int code= ResultCode.ERROR;
        String msg=null;
        if (exception instanceof AccountExpiredException){
            code=ResultCode.UNAUTHENTICATED;
            msg="账户过期";
        } else if (exception instanceof BadCredentialsException) {
            code = ResultCode.UNAUTHORIZED;
            msg = "用户名或密码错误";
        }else if (exception instanceof CredentialsExpiredException){
            code=ResultCode.UNAUTHORIZED;
            msg="密码过期";
        }else if (exception instanceof DisabledException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户被禁用";
        }else if (exception instanceof LockedException){
            code=ResultCode.UNAUTHORIZED;
            msg="账户被锁定";
        }else if (exception instanceof
                InternalAuthenticationServiceException){
            code=ResultCode.UNAUTHENTICATED;
            msg="账户不存在";
        }else if (exception instanceof CustomerAuthenticationException){
            code=ResultCode.UNAUTHORIZED;
            msg=exception.getMessage();
        }
        else {
            msg="登录失败";
        }
        String result = JSON.toJSONString(Result.fail().setCode(code).setMessage(msg));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
