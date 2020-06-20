package ru.itis.springemail.services;

import ru.itis.springemail.models.User;

public interface TokenService {

    String generateToken(User user);

    String decodeToken(String token);

    boolean verifyToken(String token);

}
