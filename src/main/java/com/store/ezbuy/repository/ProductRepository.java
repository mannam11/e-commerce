package com.store.ezbuy.repository;

import com.store.ezbuy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByBrandId(long brandId);

    List<Product> findByCategoryId(long categoryId);
}
