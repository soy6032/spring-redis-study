package com.example.redis.controller;

import com.example.redis.config.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final RedisUtil redisUtil;

    @GetMapping("/test")
    public String test(){
        // 임의의 authKey 생성
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);
        // 유효 시간(5분)동안 {email, authKey} 저장
        redisUtil.setDataExpire(authKey, "test@naver.com", 60 * 5L);

        System.out.println(authKey);
        System.out.println(redisUtil.getData(authKey));

        return "test";
    }
}
