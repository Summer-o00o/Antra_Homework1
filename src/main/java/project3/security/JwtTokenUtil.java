package project3.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final String SECRET_KEY =
            "Happy1918Friday1234Happy1918Friday1234Happy1918Friday1234Happy1918Friday1234!";

    //generate JWT token
    public String generateToken(String username, String role) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7200000))
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Claims getAllClaimsFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getBody();
        return claims;
    }


    public String getUserNameFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        String userName = claims.getSubject();
        return userName;
    }

    public String getRolesFromToken(String token){
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("role", String.class);
    }


    public boolean isTokenExpired(String token){
        Claims claims = getAllClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

}