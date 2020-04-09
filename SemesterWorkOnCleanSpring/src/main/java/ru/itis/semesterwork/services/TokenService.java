package ru.itis.semesterwork.services;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.semesterwork.models.User;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface TokenService {

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String extractUsername(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);

    String generateVerificationToken(User user);

    boolean verifyVerificationToken(String token);
}
