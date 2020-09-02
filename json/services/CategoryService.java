package com.softuni.json.services;

import com.softuni.json.dtos.*;
import com.softuni.json.entities.*;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDto);

    Category findCategoryByName(String name);

    Category findCategoryById(Long id);

    long countOfCategories();
}
