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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    public void seedCategories(CategorySeedDto[] categorySeedDto) {

        if (this.categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(categorySeedDto).forEach(e -> {
            if (this.validationUtil.isValid(e)) {
                Category category = this.modelMapper.map(e, Category.class);

                this.categoryRepository.saveAndFlush(category);
            } else {
                this.validationUtil.violations(e).stream().map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public Category findCategoryByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    @Override
    public Category findCategoryById(Long id) {
        return null;
    }

    @Override
    public long countOfCategories() {
        return this.categoryRepository.count();
    }
}
