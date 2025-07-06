package com.xzit.rental.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtils {
    // 定义JWT的密钥，用于加密和解密token
    private static final String SECRET_KEY = "student";
    // 定义token的过期时间，这里设置为30分钟
    private static final long EXPIRE_TIME = 1000L*60*30;

    /**
     * 创建一个JWT token
     * 该方法将给定的负载信息与预定义的密钥和过期时间结合，生成一个加密的token字符串
     *
     * @param payload 包含要编码的用户信息和其他数据的映射
     * @return 返回生成的JWT token字符串
     */
    public static String createToken(Map<String, Object> payload){
        // 获取当前时间
        DateTime now = DateTime.now();
        // 计算token过期时间
        DateTime newTime = new DateTime(now.getTime()+EXPIRE_TIME);

        // 在负载中添加发行时间、过期时间和生效时间
        payload.put(JWTPayload.ISSUED_AT,now);
        payload.put(JWTPayload.EXPIRES_AT,newTime);
        payload.put(JWTPayload.NOT_BEFORE,now);

        // 使用负载信息和密钥生成并返回JWT token
        return JWTUtil.createToken(payload,SECRET_KEY.getBytes());
    }

    public static JWTPayload parseToken(String token){
        // 解析并验证token，返回JWT的负载信息
        JWT jwt = JWTUtil.parseToken(token);
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()){ // 验证token的签名，确保token未被篡改
            throw new RuntimeException("token异常");
        }
        if (!jwt.validate(0)){ // 检查token是否过期
            throw new RuntimeException("token已过期");
        }
        return jwt.getPayload(); // 返回token的payload部分
    }
}
