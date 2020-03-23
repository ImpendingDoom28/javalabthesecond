package ru.itis.mail.services;

import java.io.File;

public interface EmailService {

    void sendEmail(String to, String subject, String freemarkerText);

    void sendEmailWithAttachments(String to, String subject, String freemarkerText, File... attachments);
}
