package com.rtmart.readwalk.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String SALT = "SweJids";

    private static Key generateKey() {
        return new SecretKeySpec(SALT.getBytes(),
                SignatureAlgorithm.HS512.getJcaName());
    }

    public static String generateJws(Map<String, Object> claims) {
        Key key = generateKey();
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "1");

        return Jwts.builder().setHeader(headers).setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key).compact();
    }

    public static Claims getJwtBody(String jwt) throws Exception {
        try {
            return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
