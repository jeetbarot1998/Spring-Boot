package com.demo.springboot.SpringBootApp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String secret_key = "ABCD";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 day in milliseconds

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String tokens){
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJwt(tokens).getBody();
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // Method to generate a JWT token
    public String generateToken(UserDetails userDetails) {
        return createToken(new HashMap<String, Object>(), userDetails.getUsername());
    }

    // Method to create a JWT token with expiration and claims
    public static String createToken( Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);


        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

//    // Method to validate a JWT token
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJwt(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token has expired.");
            return false;
        }
    }

//    // Method to check if a JWT token has expired
//    public static boolean isTokenExpired(String token) {
//        try {
//            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//            Date expiration = claims.getExpiration();
//            return expiration.before(new Date());
//        } catch (ExpiredJwtException e) {
//            System.out.println("Token has expired.");
//            return true;
//        }
//    }
//
//    // Method to extract all claims from a JWT token
//    public static Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
//
//    // Method to extract a specific claim from a JWT token
//    public static Object extractClaim(String token, String claimName) {
//        return extractAllClaims(token).get(claimName);
//    }
//
//    // Method to extract the username claim from a JWT token
//    public static String extractUserName(String token) {
//        return (String) extractClaim(token, USERNAME_CLAIM);
//    }

}
