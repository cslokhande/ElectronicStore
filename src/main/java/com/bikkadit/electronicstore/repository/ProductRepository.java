package com.bikkadit.electronicstore.repository;

import com.bikkadit.electronicstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
