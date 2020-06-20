package ru.itis.springemail.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import ru.itis.springemail.config.FreemarkerConfig;
import ru.itis.springemail.services.RegisterService;
import ru.itis.springemail.services.RegisterServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterServlet extends HttpServlet {

    private final String TEMPLATE_NAME = "ftl/register.ftl";
    private RegisterService registerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        ApplicationContext context = (ApplicationContext) servletContext.getAttribute("springContext");
        this.registerService = context.getBean(RegisterService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), TEMPLATE_NAME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        List<String> errors = registerService.register(email, password, name, surname, getServletContext());
        if(errors.isEmpty()) {
            req.setAttribute("email", email);
            resp.sendRedirect("/confirm");
        } else {
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }
}
