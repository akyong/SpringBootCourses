package com.bobby.demo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of demo 3 - Qualifier.
 * Licensed under the MIT License.
 */
@Component
public class TrackCoach implements  Coach {
    public TrackCoach() {
        System.out.println("In constructor: "+this.getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k!";
    }
}
