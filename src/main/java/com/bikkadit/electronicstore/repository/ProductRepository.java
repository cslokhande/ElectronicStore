package com.bikkadit.electronicstore.repository;

import com.bikkadit.electronicstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByTitleContaining(String keyword, Pageable pageable);
    Page<Product> findByLiveTrue(Pageable pageable);

}
