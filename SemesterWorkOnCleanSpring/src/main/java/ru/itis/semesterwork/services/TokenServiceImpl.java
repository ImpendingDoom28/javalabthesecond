package ru.itis.semesterwork.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.models.State;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.models.VerificationToken;
import ru.itis.semesterwork.repositories.UsersRepository;
import ru.itis.semesterwork.repositories.VerificationTokenRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    @Override
    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .user(user)
                .value(token)
                .createdAt(LocalDateTime.now())
                .build();
        Optional<VerificationToken> tokenToReturn = verificationTokenRepository.save(verificationToken);
        if(tokenToReturn.isPresent()) {
            return tokenToReturn.get().getValue();
        } else throw new IllegalStateException("Error with database happened! Maybe method not realised yet");
    }

    @Override
    public boolean verifyVerificationToken(String token) {
        Optional<VerificationToken> tokenOptional = verificationTokenRepository.findByValue(token);
        if(tokenOptional.isPresent()) {
            VerificationToken verificationToken = tokenOptional.get();
            if(verificationToken.getValue().equals(token)) {
                User toUpdate = verificationToken.getUser();
                toUpdate.setState(State.CONFIRMED);
                usersRepository.update(toUpdate);
                return true;
            }
        }
        return false;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(getCurrentTime())
                .setExpiration(getExpirationDate((long) (1000 * 60 * 60 * 10)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Date getCurrentTime() {
        return new Date(System.currentTimeMillis());
    }

    private Date getExpirationDate(long expirationPeriod) {
        return new Date(System.currentTimeMillis() + expirationPeriod);
    }
}