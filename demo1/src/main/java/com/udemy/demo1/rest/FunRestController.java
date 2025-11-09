package com.udemy.demo1.rest;/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of demo1.
 * Licensed under the MIT License.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // inject value from application.properties

    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    // expose new endpoint for "teaminfo"

    @GetMapping("teaminfo")
    public String getTeamInfo() {
        return "Coach: " + coachName + ", Team Name: " + teamName;
    }

    // expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {

        return "Hello World test satu dua tiga";
    }

    @GetMapping("/workout")
    public String workout() {
        return "Run a hard 5k";
    }

    @GetMapping("/fortune")
    public String fortune() {
        return "Today is your lucky day";
    }
}
