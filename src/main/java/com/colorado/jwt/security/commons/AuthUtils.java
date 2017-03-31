package com.colorado.jwt.security.commons;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by colorado on 30/03/17.
 */
public class AuthUtils {
    public static final boolean hasAuthority(UserDetails userDetails, String ... authorities) {
        for(String authority : authorities) {
            for(GrantedAuthority grantedAuthority: userDetails.getAuthorities()) {
                if(grantedAuthority.getAuthority().equalsIgnoreCase(authority)) {
                    return true;
                }
            }
        }
        return false;
    }
}
