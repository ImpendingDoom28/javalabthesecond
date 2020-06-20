package ru.itis.springemail.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.springemail.config.FreemarkerConfig;
import ru.itis.springemail.services.TokenService;
import ru.itis.springemail.services.TokenServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfirmServlet extends HttpServlet {

    private final String TEMPLATE_NAME = "ftl/confirm.ftl";
    private TokenService tokenService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.tokenService = applicationContext.getBean(TokenServiceImpl.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("email", req.getSession(false).getAttribute("email"));
        String token = req.getParameter("token");
        if(token != null) {
            if(tokenService.verifyToken(token)) {
                System.out.println("token verified");
            }
        }
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), TEMPLATE_NAME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
