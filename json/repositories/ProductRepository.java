package com.softuni.json.repositories;

import com.softuni.json.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product as p where p.price >= 500 and p.price <= 1000 and p.buyer is null order by p.price")
    Set<Product> findAllByPriceGreaterThanAndPriceLessThanAndBuyerIsNullOrderByPrice();
}
