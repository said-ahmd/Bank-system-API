package org.fawry.bankapisystem.security;

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
import java.util.Objects;
import java.util.function.Function;
@Service
public class JWTService {

    private final String SECRET_KEY ="305c300d06092a864886f70d0101010500034b003048024100c3f9eeeb416425606f7604dd2185cea32306a8d183224047049d45c4ca9a9b834851b07df48d09ad1003d7faf01be9623c50710737ac89030e12da0787f294ef0203010001";
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
        return Jwts
                .builder()
                .setClaims(exteraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (24*60*1000)) )
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
