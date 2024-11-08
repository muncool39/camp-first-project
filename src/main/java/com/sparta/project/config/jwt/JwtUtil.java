package com.sparta.project.config.jwt;


import static com.sparta.project.config.jwt.TokenProvider.CLAIM_USER_ID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final TokenProvider jwtProvider;

    public Long getUserIdFromJwt(String token) {
        Claims claims = getClaim(token);
        return Long.valueOf(claims.get(CLAIM_USER_ID).toString());
    }

    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getClaim(token);
            return JwtValidationType.VALID_TOKEN;
        } catch (MalformedJwtException ex) {
            return JwtValidationType.INVALID_TOKEN;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_TOKEN;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_TOKEN;
        }
    }

    private Claims getClaim(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProvider.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
