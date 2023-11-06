package com.wei.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String singKey = "songbao";
    private static Long expire = 43200000L;
    //生成jwt令牌
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, singKey) //签名算法
                .setClaims(claims) //自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //有效期
                .compact();
        return jwt;
    }

    //解析jwt令牌
    public static Map<String,Object> parserJwt(String jwt){
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey(singKey) //签名算法
                .parseClaimsJws(jwt)    //jwt令牌
                .getBody();//数据
        return claims;
    }
}
