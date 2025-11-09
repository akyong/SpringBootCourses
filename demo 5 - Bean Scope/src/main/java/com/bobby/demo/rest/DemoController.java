package com.bobby.demo.rest;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of demo2.
 * Licensed under the MIT License.
 */

import com.bobby.demo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;
    private Coach anotherCoach;

    /**
     * cricketCoach is the name of the class at com.bobby.demo.common.CricketCoach
     * this Qualifier, you can change it by yourself with another class name
     * for example, baseballCoach, tennisCoach, trackCoach
     * */
    @Autowired
    public DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theAnotherCoach) {
        System.out.println("In constructor: "+this.getClass().getSimpleName());
        this.myCoach = theCoach;
        this.anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach == anotherCoach: " + (myCoach == anotherCoach);
    }
}
