package com.candoit.dstagram.security.utils;

import com.candoit.dstagram.security.model.AuthUser;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    private static final String secretKey = "ThisisA_SecretKeyForJwtExample";

    public String generateJwtToken(AuthUser authUser) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(authUser.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(authUser))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private Date createExpireDateForOneYear() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private Map<String, Object> createClaims(AuthUser authUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", authUser.getEmail());
        return claims;
    }

    private Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String getEmailFromToken(String token) throws MalformedJwtException {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("email");
    }

    private Claims getClaimsFormToken(String token) throws MalformedJwtException {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }
}
