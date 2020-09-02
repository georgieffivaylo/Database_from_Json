package com.softuni.json.dtos;

import com.google.gson.annotations.*;
import org.hibernate.validator.constraints.*;

public class CategorySeedDto {

    @Expose
    @Length(min = 3, max = 15, message = "Wrong category name !")
    private String name;

    public CategorySeedDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
