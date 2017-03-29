package com.colorado.jwt.security.token.impl;

import com.colorado.jwt.security.token.JwtToken;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.Claims;

public final class AccessJwtToken implements JwtToken {
    private final String rawToken;
    @JsonIgnore private Claims claims;

    public AccessJwtToken(final String token, Claims claims) {
        this.rawToken = token;
        this.claims = claims;
    }

    public String getToken() {
        return this.rawToken;
    }

    public Claims getClaims() {
        return claims;
    }
}
