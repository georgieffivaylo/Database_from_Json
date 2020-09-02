package com.softuni.json.dtos;

import com.google.gson.annotations.*;

import javax.validation.constraints.*;
import java.math.*;

public class ProductSeedDto {

    @Expose
    @NotNull (message = "Name cannot be Null value !")
    private String name;
    @Expose
    private BigDecimal price;


    public ProductSeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
