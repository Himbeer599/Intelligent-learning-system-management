package com.learn.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "aXRoZWltYQ==";
//
//    private static final String SECRET = "f8UeJ9LmN2qWzXpK4VaQc6RyBsDhT7Go123";
//    private static final SecretKey SECRET_KEY =  Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static Long expire = 43200000L;


    // generate jwt card (encode)
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }
    //decode jwt card
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

}
