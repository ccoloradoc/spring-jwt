package com.colorado.jwt.security.endpoint;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.colorado.jwt.security.converters.UserToUserDetailsConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.colorado.jwt.security.auth.jwt.extractor.TokenExtractor;
import com.colorado.jwt.security.auth.jwt.verifier.TokenVerifier;
import com.colorado.jwt.security.config.JwtSettings;
import com.colorado.jwt.security.config.WebSecurityConfig;
import com.colorado.jwt.security.exceptions.InvalidJwtToken;
import com.colorado.jwt.security.token.JwtToken;
import com.colorado.jwt.security.token.JwtTokenFactory;
import com.colorado.jwt.security.token.impl.RawAccessJwtToken;
import com.colorado.jwt.security.token.impl.RefreshToken;

@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    private UserToUserDetailsConverter converter;

    private UserDetailsService userDetailsService;

    @Autowired
    public void setConverter(UserToUserDetailsConverter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);

        return tokenFactory.createAccessJwtToken(userDetails);
    }
}
