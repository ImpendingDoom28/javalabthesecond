package ru.itis.semesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.security.details.UserDetailsImpl;
import ru.itis.semesterwork.services.UserService;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{user-id}")
    public ModelAndView getProfile(@PathVariable("user-id") Long userId, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            User user = userService.findById(userId);
            modelAndView.addObject("user", UserDto.from(user));
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("user", null);
        }
        UserDetails userDetails = (UserDetailsImpl)authentication.getPrincipal();
        modelAndView.addObject("authUser", userDetails);
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
