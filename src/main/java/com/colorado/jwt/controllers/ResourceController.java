package com.colorado.jwt.controllers;

import com.colorado.jwt.security.auth.JwtAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by colorado on 28/03/17.
 */
@RestController
public class ResourceController {

    @RequestMapping("/api/resource")
    public Resource findResource() {
        return new Resource("Hello authenticated user");
    }

    @RequestMapping(value="/api/me", method = RequestMethod.GET)
    public UserDetails get(JwtAuthenticationToken token) {
        return (UserDetails) token.getPrincipal();
    }

    public class Resource {
        private String message;

        private Resource(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
