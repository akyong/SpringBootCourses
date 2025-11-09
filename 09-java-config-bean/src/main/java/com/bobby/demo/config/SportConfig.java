package com.bobby.demo.config;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

import com.bobby.demo.common.Coach;
import com.bobby.demo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    // define a bean for the dependency
    // to able injected in controller
    @Bean //use @Bean("otherName") and you use @Qualifier("otherName") to inject it
    public Coach swimCoach() {
        return new SwimCoach();
    }


}
