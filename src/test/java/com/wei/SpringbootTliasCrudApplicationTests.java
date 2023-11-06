package com.wei;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootTliasCrudApplicationTests {

    @Test
    void contextLoads() {
    }


    //生成JWT令牌
    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id","1");
        claims.put("name","songsong");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itheima") //签名算法
                .setClaims(claims) //自定义内容(载荷数据)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置jwt令牌有效期
                .compact();
        System.out.println(jwt);
    }

    //解析JWT令牌
    @Test
    public void testParserJwt(){
        Map<String, Object> claims = Jwts.parser()
                .setSigningKey("itheima") //签名密钥
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic29uZ3NvbmciLCJpZCI6IjEiLCJleHAiOjE2OTkyNjA3MzN9.6v9oYaIP153_x8wLwQr3uFoSOayJVZA-vqdpD3HHwFc") //toke
                .getBody(); //获取载荷数据
        System.out.println(claims);
    }
}
