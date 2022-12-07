package com.example.eatusapi.security.jwt;

import com.example.eatusapi.account.dto.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;

import javax.annotation.PostConstruct;
import java.security.Key;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
@Component
public class TokenProvider {
    private String secretKey = "rlaekal";
    private long tokenValidTime = 30 * 60 * 1000l;
    private final UserDetailsService userDetailsService;
    private Key key;
    private Logger logger = LoggerFactory.getLogger("JWT_LOGGER");

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parsePlaintextJws(token);
            return true;
        } catch (SecurityException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}
