package com.cyriii.filter;

import com.cyriii.common.RedisPrefix;
import com.cyriii.utils.JWTUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final List<GrantedAuthority> grantedAuthorityList = new ArrayList<>(0);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        if(StringUtils.isNotEmpty(token)){
            Map<String, String> dataMap = JWTUtils.verifyToken(token);
            // 从redis取出token进行比对
            if(dataMap != null && dataMap.size() != 0 && StringUtils.isNotEmpty(dataMap.get("userName"))){
                String realToken = stringRedisTemplate.opsForValue().get(RedisPrefix.TOKEN + dataMap.get("userName"));
                if(StringUtils.equals(token, realToken)){
                    usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dataMap.get("userName"), null, grantedAuthorityList);
                }
            }
        }
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
