package ru.itis.springemail.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class FreemarkerConfig {

    private static Configuration configuration;

    private FreemarkerConfig(){}

    public static Configuration getInstance(String pathToTemplates) {
        if(configuration == null) {
            configuration = new Configuration(Configuration.VERSION_2_3_29);
            configurateConfig(configuration, pathToTemplates);
        }
        return configuration;
    }

    private static void configurateConfig(Configuration configuration, String pathToTemplates) {
        // Create your Configuration instance, and specify if up to what FreeMarker
        // version (here 2.3.29) do you want to apply the fixes that are not 100%
        // backward-compatible. See the Configuration JavaDoc for details.

        // Specify the source where the template files come from. Here I set a
        // plain directory for it, but non-file-system sources are possible too:
        File file = new File(pathToTemplates);
        try {
            configuration.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        // From here we will set the settings recommended for new projects. These
        // aren't the defaults for backward compatibilty.

        // Set the preferred charset template files are stored in. UTF-8 is
        // a good choice in most applications:
        configuration.setDefaultEncoding("UTF-8");

        // Sets how errors will appear.
        // During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // Don't log exceptions inside FreeMarker that it will thrown at you anyway:
        configuration.setLogTemplateExceptions(false);

        // Wrap unchecked exceptions thrown during template processing into TemplateException-s:
        configuration.setWrapUncheckedExceptions(true);

        // Do not fall back to higher scopes when reading a null loop variable:
        configuration.setFallbackOnNullLoopVariable(false);
    }

    public static void preprocessConfig(Writer out, Map<String, Object> root, ServletContext servletContext, String templateName) {
        Template template = null;
        try {
            template = FreemarkerConfig.getInstance(servletContext.getRealPath("/WEB-INF/frontend/")).getTemplate(templateName);
            template.process(root, out);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}