package com.example.eatusapi.security.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TokenProvider {
   private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
   private final UserDetailsService userDetailsService;

   private String secretKey = "kimtamiangiyoungsinsiakimtamiangiyoungsinsia";
   private final long validsecond = 1000l*60*60;

   @PostConstruct
    protected void init()
   {
       logger.info("secret key 초기화 시작");
       secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
       logger.info("secret key 초기화 종료");
   }

   public String createToken(String uid, List<String> roles)
   {
       logger.info("토큰 생성 시작");
       Claims claim = Jwts.claims().setSubject(uid);
       claim.put("roles",roles);

       Date now = new Date();

       String token = Jwts.builder()
               .setClaims(claim)
               .setIssuedAt(now)
               .setExpiration(new Date(now.getTime()+validsecond))
               .signWith(SignatureAlgorithm.HS256,secretKey)
               .compact();

       logger.info("토큰 생성 종료");

       return token;
   }

   public Authentication getAuthentication(String token)
   {
       logger.info("인증 정보 조회");
       UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
       logger.info("인증 정보 조회 완료");

       return new UsernamePasswordAuthenticationToken(userDetails,"",
               userDetails.getAuthorities());
   }

   public String getUsername(String token)
   {
       String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody()
               .getSubject();
       return info;
   }

   public String resolveToken(HttpServletRequest request)
   {
       return request.getHeader("X-AUTH_TOKEN");
   }

   public boolean validateToken(String token)
   {
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        }catch(Exception e)
        {
            logger.info("토큰 유효 체크 에러 발생");
            return false;
        }
   }
}
