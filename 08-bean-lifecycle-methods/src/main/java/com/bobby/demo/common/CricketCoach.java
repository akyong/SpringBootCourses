package com.bobby.demo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of demo2.
 * Licensed under the MIT License.
 */

@Component
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In constructor: "+this.getClass().getSimpleName());
    }

    // define init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff():"+this.getClass().getSimpleName());
    }


    // define destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff():"+this.getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes! !~";
    }
}
