package com.store.ezbuy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.ezbuy.dto.ProductRequestDto;
import com.store.ezbuy.dto.ProductResponseDto;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.exceptions.custom_exceptions.InvalidCategoryOrBrandException;

import java.util.List;

public interface ProductService {

    public List<ProductResponseDto> findAllProducts() throws JsonProcessingException;

    public void createProduct(ProductRequestDto productDto) throws InvalidCategoryOrBrandException;

    public boolean deleteProduct(long id);

    public Product getProduct(long id) throws JsonProcessingException;

    public boolean updateProduct(long id, ProductRequestDto productDto) throws InvalidCategoryOrBrandException;

    public List<Product> getProductsByBrand(long brandId);

    public List<Product> getProductsByCategory(long categoryId);

    public Product getProductByID(long id);
}
