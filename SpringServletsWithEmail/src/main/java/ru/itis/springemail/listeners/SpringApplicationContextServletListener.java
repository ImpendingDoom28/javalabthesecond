package ru.itis.springemail.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.springemail.config.ApplicationContextConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SpringApplicationContextServletListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("IN LISTENER");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("springContext", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
