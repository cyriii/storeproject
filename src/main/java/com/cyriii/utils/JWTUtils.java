package com.cyriii.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static final String SECRET = "8491023jhzkxch-92-$@#901h23k4";

    /**
     * 生成token
     * @return
     */
    public static String createToken(Map<String, String> dataMap){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withExpiresAt(DateUtils.addHours(new Date(), 2));// 设置过期时间为两个小时 可以放在配置文件中 此处采用硬编码
            dataMap.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (Exception e){
            return null;
        }
    }

    /**
     * 验证token，返回数据
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, String> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>(map.size());
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("userName", "lyj");
        dataMap.put("uuid", UUIDUtils.getUUID());
        String token = createToken(dataMap);

        Map<String, String> tempMap = verifyToken(token);
        tempMap.forEach((k, v) -> System.out.println(k + ": " + v));

    }
}
