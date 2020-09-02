package com.softuni.json.services;

import com.softuni.json.dtos.*;
import com.softuni.json.entities.*;
import com.softuni.json.repositories.*;
import com.softuni.json.utils.*;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.validation.*;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final Random random;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, Random random, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.random = random;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {

        if(this.productRepository.count() != 0) {
            return;
        }

        Arrays.stream(productSeedDtos).forEach(p -> {
            if (this.validationUtil.isValid(p)){
                Product product = this.modelMapper.map(p, Product.class);

                long randomCategoryId = this.random.nextInt((int) this.categoryService.countOfCategories()) + 1;

                for (int i = 0; i < 3; i++) {
                    Category category = this.categoryService.findCategoryById(randomCategoryId);
                    try {
                        product.getCategories().add(category);
                    }catch (NullPointerException e){

                    }

                }


                User seller = this.userService.findById((long) randomId((int) this.userService.userCount()) + 1);
                product.setSeller(seller);
                try {
                    User buyer = this.userService
                            .findById((long) randomId((int) this.userService.userCount() * 2) + 1);

                    product.setBuyer(buyer);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                this.productRepository.saveAndFlush(product);
            }else {
                this.validationUtil.violations(p).stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    private int randomId(int range){
        return this.random.nextInt(range);
    }
}
