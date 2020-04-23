package ru.itis.sockjschat.services;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPasswordBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashPass = new StringBuilder();
            for (int i = 0; i < hashedPasswordBytes.length; i++) {
                hashPass.append((char)hashedPasswordBytes[i]);
            }
            System.out.println(hashPass.toString());
            return hashPass.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm!!!");
        }
    }

    @Override
    public boolean compare(String given, String passwordFromDatabase) {
        System.out.println(hashPassword(given));
        return hashPassword(given).equals(passwordFromDatabase);
    }
}
