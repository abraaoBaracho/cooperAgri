package br.com.cooperagri.security.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cooperagri.services.impl.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${cooperagri.jwtSecret}")
    private String jwtSecret;

    @Value("${cooperagri.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String gerarTokenComUserDetailsImpl(UserDetailsImpl userData) {
        return Jwts.builder()
                .subject(userData.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey())
                .compact();

    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        return key;
    }

    @SuppressWarnings("deprecation")
    public String getCpfToken(String token){
        return  Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    @SuppressWarnings("deprecation")
    public boolean validateJwtToken(String authToken) {
        try {

            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;

        } catch (MalformedJwtException e) {
            System.out.println("Token invalido" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token não suportado " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token Argumento inválido " + e.getMessage());
        }
        return false;
    }

}
