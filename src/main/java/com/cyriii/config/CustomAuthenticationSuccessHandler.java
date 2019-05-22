package com.cyriii.config;

import com.cyriii.common.RedisPrefix;
import com.cyriii.common.ResultMessage;
import com.cyriii.service.SysUserService;
import com.cyriii.utils.JWTUtils;
import com.cyriii.utils.UUIDUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 自定义登录成功返回内容
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 登录成功返回信息
        ResultMessage resultMessage = new ResultMessage();

        Map<String, Object> dataMap = new HashMap<>();

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("userName", authentication.getName());
        String uuid = UUIDUtils.getUUID();
        tokenMap.put("uuid", uuid);
        String token = JWTUtils.createToken(tokenMap);

        // 将token缓存进redis中
        stringRedisTemplate.opsForValue().set(RedisPrefix.TOKEN + authentication.getName(), token, 2, TimeUnit.HOURS);  // 过期时间设置为2小时，可以写入配置文件中获取
        stringRedisTemplate.opsForValue().set(RedisPrefix.USER_ID + authentication.getName(), sysUserService.getIdByUserName(authentication.getName()), 2, TimeUnit.HOURS);   // 将用户id缓存进redis

        dataMap.put("token", token);
        resultMessage.setData(dataMap);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(resultMessage));
    }
}
