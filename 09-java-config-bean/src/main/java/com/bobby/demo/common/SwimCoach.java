package com.bobby.demo.common;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

//Not using @Component

public class SwimCoach implements Coach{


    public SwimCoach() {
        System.out.println("In constructor: "+this.getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up!";
    }
}
