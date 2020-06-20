package ru.itis.springemail.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;
import ru.itis.springemail.models.User;

@Component
public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(User user) {
        Algorithm algorithmHS = Algorithm.HMAC256("aniMafia");
        String token = JWT.create()
                .withSubject("" + user.getId())
                .withClaim("email", user.getEmail())
                .withIssuer("auth0")
                .sign(algorithmHS);
        return token;
    }

    @Override
    public String decodeToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        Claim claim = jwt.getClaim("email");
        System.out.println(claim.asString());
        return claim.asString();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("aniMafia");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e){
            throw new IllegalStateException(e);
        }
    }
}
