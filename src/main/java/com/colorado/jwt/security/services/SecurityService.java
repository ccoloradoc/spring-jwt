package com.colorado.jwt.security.services;

/**
 * Created by colorado on 28/03/17.
 */
public interface SecurityService {
    String encryptString(String input);
    boolean checkPassword(String plainPassword, String encryptedPassword);
}
