package br.com.ponto.pontoeletronico.dto;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

public class TokenGw {

    private String idToken;

    public TokenGw(String tokenString) {
        Jwt jwt = JwtHelper.decode(tokenString);
        String token = jwt.getClaims().substring(jwt.getClaims().lastIndexOf("id") + 5);
        this.idToken = token.substring(0, token.indexOf(','));
    }

    public String getIdToken() {
        return idToken;
    }

    @Override
    public String toString() {
        return "'Token_id':'" + idToken + '\'' +
                '}';
    }
}