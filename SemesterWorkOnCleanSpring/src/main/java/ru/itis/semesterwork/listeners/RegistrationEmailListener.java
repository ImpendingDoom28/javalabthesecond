package ru.itis.semesterwork.listeners;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.semesterwork.events.OnRegisterSuccessEvent;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.services.EmailService;
import ru.itis.semesterwork.services.TokenService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationEmailListener implements ApplicationListener<OnRegisterSuccessEvent> {

    private final String SUBJECT_MESSAGE = "Registration confirmation";
    @Autowired
    private TokenService tokenService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void onApplicationEvent(OnRegisterSuccessEvent onRegisterSuccessEvent) {
        this.confirmRegistration(onRegisterSuccessEvent);
    }

    private void confirmRegistration(OnRegisterSuccessEvent onRegisterSuccessEvent) {
        System.out.println("In Confirm Register");
        User user = onRegisterSuccessEvent.getUser();
        String finalToken = tokenService.generateVerificationToken(user);
        String url = onRegisterSuccessEvent.getContextPath() + "http://localhost:3000" + "/auth?confirmationToken=" + finalToken;
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/email-templates/");
        try {
            Template template = freemarkerConfig.getTemplate("email_verify.ftlh");
            Map<String, Object> root = new HashMap<>();
            root.put("url", url);
            emailService.sendEmail(user.getEmail(),
                    SUBJECT_MESSAGE,
                    FreeMarkerTemplateUtils.processTemplateIntoString(template, root));
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
