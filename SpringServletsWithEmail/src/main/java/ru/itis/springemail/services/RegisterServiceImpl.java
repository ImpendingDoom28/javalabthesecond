package ru.itis.springemail.services;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.springemail.config.FreemarkerConfig;
import ru.itis.springemail.email.EmailSubjects;
import ru.itis.springemail.models.Mail;
import ru.itis.springemail.models.User;
import ru.itis.springemail.repositories.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Component(value = "registerService")
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TokenService tokenService;

    @Override
    public List<String> register(String email, String password, String name, String surname, ServletContext servletContext) {
        List<String> errors = new ArrayList<>();
        if(password.length() == 0) {
            errors.add("Password should contain some characters, lol");
        }
        if(errors.isEmpty()) {
            Optional<User> registeredUser = userRepository.save(User.builder()
                    .email(email)
                    .name(name)
                    .password(password)
                    .surname(surname)
                    .build());
            if(registeredUser.isPresent()) {
                try {
                    Mail mail = Mail.builder()
                        .to(name.toUpperCase().charAt(0) + name.substring(1) + " " + surname.toUpperCase().charAt(0) + surname.substring(1))
                        .link("https://localhost:8080/confirm?token=" + tokenService.generateToken(registeredUser.get()))
                        .build();
                    Template template = FreemarkerConfig.getInstance(servletContext.getRealPath("/WEB-INF/frontend/")).getTemplate("ftl/email/confirmation.ftl");
                    Map<String, Object> root = new HashMap<>();
                    root.put("mail", mail);
                    emailService.sendEmail(email, EmailSubjects.CONFIRMATION.name(),
                            FreeMarkerTemplateUtils.processTemplateIntoString(template, root));
                } catch (IOException | TemplateException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
        return errors;
    }
}
