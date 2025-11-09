package com.bobby.demo.common;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of demo 3 - Qualifier.
 * Licensed under the MIT License.
 */

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
    public TennisCoach() {
        System.out.println("In constructor: "+this.getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }
}
