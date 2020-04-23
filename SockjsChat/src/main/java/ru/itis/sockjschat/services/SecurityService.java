package ru.itis.sockjschat.services;

public interface SecurityService {

    String hashPassword(String password);

    boolean compare(String given, String passwordFromDatabase);
}
