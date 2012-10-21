package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.User;
import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    UserService userService;

    @Autowired
    public LoginController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("templates/login");
        return modelAndView;
    }

    @RequestMapping(value="login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String email, @RequestParam("password") String password){
        ModelAndView modelAndView = new ModelAndView("templates/main_page");
        User user = userService.getUserByEmail(email);
        if(user == null || !user.getPassword().equals(password)){
            modelAndView.getModelMap().addAttribute("message", "Authentication Failed");
        }
        else{
            modelAndView.addObject(user);
        }
        return modelAndView;
    }

}
