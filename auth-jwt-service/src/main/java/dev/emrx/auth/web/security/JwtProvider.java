package dev.emrx.auth.web.security;

import dev.emrx.auth.domain.RequestDto;
import dev.emrx.auth.model.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    private Key secretKey;

    @Autowired
    private RouteValidator routeValidator;

    @PostConstruct
    protected void init() {
//        secret = Base64.getEncoder().encodeToString(secret.getBytes());

//        clave secreta aleatorio
        byte[] apiKeySecretBytes = new byte[64];
        new SecureRandom().nextBytes(apiKeySecretBytes);
        secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    }

    public String createToken(AuthUser authUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", authUser.getId());
        claims.put("role", authUser.getRole());

        Date now = new Date();
        return Jwts.builder()
                .claims(claims)
                .subject(authUser.getUsername())
                .issuedAt(now)
                .expiration(generarFechasExpiracion())
                .signWith(Keys.hmacShaKeyFor(secretKey.getEncoded()))
                .compact();

//        Map<String, Object> claims = new HashMap<>();
//        claims.put("id", authUser.getId());
//        claims = Jwts.claims().setSubject(authUser.getUsername()).build();
//
//        Date now = new Date();
////        Date exp = new Date(now.getTime() + 1000 * 60 * 60 * 10);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(Date.from(generarFechasExpiracion()))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
    }

    private Date generarFechasExpiracion() {
        return Date.from(LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-05:00")));
    }

    public boolean validate(String token, RequestDto requestDto) {
        try {
//            Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token);
//            return true;
            Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getEncoded())).build()
                .parseSignedClaims(token);
        } catch (Exception ex) {
            return false;
        }

        if (!isAdmin(token) && routeValidator.isAdmin(requestDto)) {
            return false;
        }
        return true;
    }

    private boolean isAdmin(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getEncoded())).build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role").toString().toUpperCase().equals("ADMIN");
    }

    public String getUsernameFromToken(String token) {
        try {
//            return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
            return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secretKey.getEncoded())).build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (Exception ex) {
            return "Bad token";
        }
    }

}
