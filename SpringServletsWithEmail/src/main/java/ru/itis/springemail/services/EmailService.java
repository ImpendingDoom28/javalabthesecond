package ru.itis.springemail.services;

public interface EmailService {

    void sendEmail(String to, String subject, String freemarkerText);

}
