package ru.itis.mail.aop;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mail.services.EmailService;
import ru.itis.mail.services.FilesService;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

/*
    Так как мы реализуем интерфейс AfterReturningAdvice,
    наш метод сработает только после того, как метод,
    который загружает файл, выполнится.
 */
@Component
public class AfterFileUploadAspect implements AfterReturningAdvice {

    @Autowired
    private EmailService emailService;
    @Autowired
    private Configuration freemarkerConfig;

    //Args - MultipartFile
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        FilesService castedTarget = (FilesService) target;
        MultipartFile multipartFile = (MultipartFile) args[0];
        File file = castedTarget.getFile(multipartFile.getOriginalFilename());
        freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/email-templates/");
        Template template = freemarkerConfig.getTemplate("file_upload.ftl");
        emailService.sendEmailWithAttachments("mikheevs11@gmail.com",
                "FILE UPLOAD",
                FreeMarkerTemplateUtils.processTemplateIntoString(template, new HashMap<String, Object>()),
                file);
    }
}
