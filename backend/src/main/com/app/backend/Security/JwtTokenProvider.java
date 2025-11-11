package com.app.backend.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("$(jwt.expiration)")
    private Long jwtExpiration;

    private SecretKey getSigninKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());

    }
public String generateToken(Authentication authentication){
    String username = authentication.getName();
    Date now =new Date();
    Date expiDate = new Date(now.getTime()+jwtExpiration);
    
    return Jwt.builder()
    .subject(username)
    .issuedAT()
    .expiration(expiryDate)
    .signWith(getSigningKey())
    .compact();
}


public String getUsernameFromToken(String token)
{Claims claims = jwt.parser()
    .verifyWith(getSigningKey())
    .build()
    .parseSignedClaims(token)
    .getPayload();

    return claims.getSubject();
}


public boolean validateToken(String authToken){
    try{
        jwt.parser()
        .verifyWith(getSigninKey())
        .build()
        .parseSignedClaims(authToken);
    }
    catch(jwtException | IllegalArgumentException e){
        return false;
    }
}


}