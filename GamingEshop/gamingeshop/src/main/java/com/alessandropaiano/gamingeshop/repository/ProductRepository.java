package com.alessandropaiano.gamingeshop.repository;

import com.alessandropaiano.gamingeshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}