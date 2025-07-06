package com.xzit.rental.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration // 表示这是一个配置类，通常用于Spring框架中配置Bean等。
@Data // Lombok提供的注解，用于自动生成getter、setter、toString等方法。
public class PasswordConfig {
    // 通过@Value注解从应用的配置文件中动态注入加密强度值。
    @Value("${encoder.ctype.strength}")
    private int strength;

    // 通过@Value注解从应用的配置文件中动态注入加密使用的密钥。
    @Value("${encoder.ctype.secret}")
    private String secret;

    /**
     * 创建并返回一个BCryptPasswordEncoder实例。
     * 此密码编码器使用指定的加密强度和基于secret参数生成的SecureRandom实例。
     *
     * @return BCryptPasswordEncoder 返回一个配置好的BCryptPasswordEncoder实例。
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 使用secret生成一个SecureRandom实例，以增加加密过程的随机性。
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength, secureRandom);
    }


}

