package com.microservice.authservice.security.impl;


import com.microservice.authservice.model.Role;
import com.microservice.authservice.security.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

//    private String secret = "ssfghfghfghfghfghfgha";


    public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10) ) //10h
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }


    public String generateRefreshToken(Map<String, Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10 * 7) ) //7d
                .signWith(getSignKey(),SignatureAlgorithm.HS256)
                .compact();
    }




    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //key = securesecuresecuresecuresecuresecure = 736563757265736563757265736563757265736563757265736563757265736563757265

    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("72797274797274716e3565343533343572797274797274716e3565343533343572797274797274716e3565343533343572797274797274716e35653435333435");
        return Keys.hmacShaKeyFor(key);
    }
    public String createToken(UserDetails username, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return generateToken(claims, username);
    }

    public String createRefreshToken(UserDetails username, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return generateRefreshToken(claims, username);
    }


    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }


    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }





    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
