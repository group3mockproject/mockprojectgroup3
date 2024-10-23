package com.example.apartmentmanagement.service.permission.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Service for managing JSON Web Tokens (JWT).
 * This class provides methods for generating and validating JWT tokens.
 * <p>
 * Author: KhangDV
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "MockProjectGroup3333333333333333333333333333333333333333333";

    /**
     * Extracts the email from the JWT token.
     *
     * @param token The JWT token.
     * @return The email extracted from the token.
     */
    public String extractEmail(String token) {
        try {
            return extractClaims(token, Claims::getSubject);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Extracts claims from the JWT token using a claims resolver function.
     *
     * @param token          The JWT token.
     * @param claimsResolver The function to resolve claims.
     * @param <T>            The type of the resolved claim.
     * @return The resolved claim.
     */
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        try {
            final Claims claims = extarctAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Generates a JWT token for the user.
     *
     * @param userDetails The user details.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails){
        Map<String, String> claims = new HashMap<>();
        claims.put("email", userDetails.getUsername());
        // Convert danh sách các vai trò (roles) thành chuỗi
        String roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        claims.put("roles", roles);
        return generateToken(claims, userDetails);
    }

    /**
     * Generates a JWT token with extra claims and user details.
     *
     * @param extraClaims The extra claims to include in the token.
     * @param userDetails The user details.
     * @return The generated JWT token.
     */
    public String generateToken(
            Map<String, String> extraClaims,
            UserDetails userDetails
    ) {
        try {
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            throw new JwtException("Error generating token", e);
        }
    }


    /**
     * Validates the JWT token against user details.
     *
     * @param token       The JWT token.
     * @return True if the token is valid, false otherwise.
     */
    public boolean isTokenValid(String token){
        return !isTokenExpired(token);
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token.
     * @return The expiration date.
     */
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token.
     * @return The claims.
     */
    private Claims extarctAllClaims(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Gets the signing key used to sign the JWT token.
     *
     * @return The signing key.
     */
    private Key getSignKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
}