package com.edu.Javier.SpaceCourier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {


     @GetMapping("/init")
    public ModelAndView home() {
        ModelAndView init = new ModelAndView("MainPage");
        return init;
    }
}