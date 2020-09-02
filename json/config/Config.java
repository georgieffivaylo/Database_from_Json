package com.softuni.json.config;

import com.google.gson.*;
import com.softuni.json.utils.*;
import org.modelmapper.*;
import org.springframework.context.annotation.*;

import java.util.*;

@Configuration
public class Config {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Gson gson(){
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    @Bean
    public Random random(){
        return new Random();
    }
}
