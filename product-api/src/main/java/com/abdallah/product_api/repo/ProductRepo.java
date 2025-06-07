package com.abdallah.product_api.repo;

import com.abdallah.product_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
