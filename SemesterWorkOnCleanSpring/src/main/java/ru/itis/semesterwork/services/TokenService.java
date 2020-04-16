package ru.itis.semesterwork.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.security.jwt.details.UserDetailsImpl;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

public interface TokenService {

    Optional<Claims> validateToken(String token);

    String generateToken(UserDetailsImpl userDetailsImpl);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);

    String generateVerificationToken(User user);

    boolean validateVerificationToken(String token);
}
