package com.nocountry.movenow.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class JwtService {

    private static final String SECRET_KEY = "L+MrTVHT22AS9RNH50pSx2xIrW+ETiojxxiuUJfJWjUvQrKqN5x5/XG7mZfPcTRWkmZRkHGOZ467LDysEYOF9s2cMBaHUPn1LkHIF9A15/0VjgYRa6XTpWjQka109x8ZSPOvkgOsA1oamztcxqP7Term/LKO9rDmzb4Yj3m/FklfvR00mR/gnvg5HlQ8nmCzkv/33gR1BK0mnMI2CP+59pWeN/+RPw8UVOBCTS9x8ovVNuMjd0EBPn/sWlWQhT4riQh/XMjNEEQV/6pNNLLls74pHPfetho4Nbtsq/uacwPvH3nJIr+8mcIzw1sBxyqqeuzIwbU8MexFa060L0742E0oi9TlwMRdsdhNIfyCulU=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims ,
            UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(Objects.requireNonNull(userDetails).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();


    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
