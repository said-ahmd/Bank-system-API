package org.fawry.bankapisystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
@Service
public class JWTService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.expiration}")
    private Long jwtEpiration;


    public String getUserEmail(String jwtToken) {
        return extractClaim(jwtToken,Claims->Claims.getSubject());
    }
    private Date getExpiration(String jwtToken) {
        return extractClaim(jwtToken,Claims->Claims.getExpiration());
    }

    public String generateToken(
            Map<String,Objects>exteraClaims,
            UserDetails userDetails
    ){

        List<String> authorities = userDetails.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toList();
        return Jwts
                .builder()
                .setClaims(exteraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtEpiration) )
                .claim("authurities", authorities)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String generateToken( UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    private <T> T extractClaim(String jwtToken, Function<Claims,T> claimResolver) {
        final Claims allClaims = extractAllClaims(jwtToken);
        return claimResolver.apply(allClaims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private boolean isTokenExpired(String jwtToken) {
        return getExpiration(jwtToken).before(new Date());
    }

    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        String userEmail = getUserEmail(jwtToken);
        return userEmail.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken) ;
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
