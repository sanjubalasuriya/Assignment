package com.microservice.apigateway.jwtUtil;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JWTServiceUtil {

    //key = securesecuresecuresecuresecuresecure = 736563757265736563757265736563757265736563757265736563757265736563757265

    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("72797274797274716e3565343533343572797274797274716e3565343533343572797274797274716e3565343533343572797274797274716e35653435333435");
        return Keys.hmacShaKeyFor(key);
    }

    public void validateToken(final String token){
         Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

}
