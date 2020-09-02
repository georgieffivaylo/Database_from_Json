package com.softuni.json.controller;

import com.google.gson.*;
import com.softuni.json.dtos.*;
import com.softuni.json.services.*;
import com.softuni.json.utils.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;

import java.io.*;

@Controller
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AppController(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, CategoryService categoryService, UserService userService, ProductService productService) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedDatabase();

    }

    private void seedDatabase() throws FileNotFoundException {
        this.seedCategories();
        this.seedUsers();
        this.seedProducts();
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] productSeedDtos =
                this.gson
                        .fromJson(new FileReader("src/main/resources/files/products.json"), ProductSeedDto[].class);

            this.productService.seedProducts(productSeedDtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] userSeedDtos =
                this.gson
                .fromJson(new FileReader("src/main/resources/files/users.json"), UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] categorySeedDtos =
                this.gson
                        .fromJson(new FileReader("src/main/resources/files/categories.json"), CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }
}
