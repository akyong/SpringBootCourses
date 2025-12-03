package com.bobbyakyong.thymeleaf.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */
@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form
//    @RequestMapping("/showForm")
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form"; //it goes to /templates/helloworld-form.html
    }

    // need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm(){
//        System.out.println("============== ");
//        System.out.println("name = "+params);
        return "helloworld";
    }
    // need a controller method to read form data and
    // add data to the model

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        // read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        // convert the data to all caps
        theName = theName.toUpperCase();
        // create the message
        String result = "Yo! " + theName;
        // add message to the model
        model.addAttribute("message", result);
        return "helloworld";

    }

    @PostMapping("/processFormVersionThree")
    public String letsShoutDuded(@RequestParam("studentName") String theName , Model model) {
        // read the request parameter from the HTML form
//        String theName = request.getParameter("studentName");
        // convert the data to all caps
        theName = theName.toUpperCase();
        // create the message
        String result = "Yo!  v3 " + theName;
        // add message to the model
        model.addAttribute("message", result);
        return "helloworld";

    }
}

