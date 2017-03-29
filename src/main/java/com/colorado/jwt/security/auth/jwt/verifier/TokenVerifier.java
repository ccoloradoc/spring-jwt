package com.colorado.jwt.security.auth.jwt.verifier;

public interface TokenVerifier {
    boolean verify(String jti);
}
