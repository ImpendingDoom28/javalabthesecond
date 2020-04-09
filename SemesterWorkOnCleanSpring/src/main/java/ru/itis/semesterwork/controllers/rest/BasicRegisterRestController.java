package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.semesterwork.services.RegisterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BasicRegisterRestController implements Controller {

    @Autowired
    private RegisterService registerService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getParts().forEach(System.out::println);
        System.out.println(request.getParameterNames().nextElement());
        System.out.println(request.getMethod());
        return new ModelAndView();
    }


}
