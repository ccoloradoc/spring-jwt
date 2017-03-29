package com.colorado.jwt.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
