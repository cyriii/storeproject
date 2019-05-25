package com.cyriii.utils;

import com.cyriii.common.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.cyriii.utils.JWTUtils.verifyToken;

/**
 * 获取用户信息工具
 */
@Component
public class UserUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取用户ID
     * @return
     */
    public String currentUserId()  {
        String token = ContextUtils.getToken();
        if(token==null|| verifyToken(token)==null){
            return null;
        }
        Map<String, String> map = JWTUtils.verifyToken(token);
        return map.get("userId");
    }


}
