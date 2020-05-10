package ru.itis.semesterwork.security.jwt.provider;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.semesterwork.models.Role;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.security.jwt.authentication.JwtAuthentication;
import ru.itis.semesterwork.security.jwt.details.UserDetailsImpl;
import ru.itis.semesterwork.services.TokenService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Optional<Claims> claimsOptional = tokenService.validateToken(token);
        if(claimsOptional.isPresent()) {
            Claims claims = claimsOptional.get();
            System.out.println(claims);

            ArrayList object = claims.get("role", ArrayList.class);
            String email = claims.get("email", String.class);
            Long id = claims.get("id", Long.class);

            UserDetails userDetails = UserDetailsImpl.builder()
                    .user(User.builder()
                            .nickname(claims.getSubject())
                            .role(Role.valueOf(((LinkedHashMap)object.get(0)).get("authority").toString()))
                            .email(email)
                            .id(id)
                            .build())
                    .build();
            authentication.setAuthenticated(true);
            ((JwtAuthentication)authentication).setUserDetails(userDetails);
        } else {
            authentication.setAuthenticated(false);
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
