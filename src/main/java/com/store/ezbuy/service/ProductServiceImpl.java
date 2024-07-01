package com.store.ezbuy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.ezbuy.dto.ProductRequestDto;
import com.store.ezbuy.dto.ProductResponseDto;
import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.entity.Category;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.exceptions.custom_exceptions.InvalidCategoryOrBrandException;
import com.store.ezbuy.repository.BrandRepository;
import com.store.ezbuy.repository.CategoryRepository;
import com.store.ezbuy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Value("${products.cache.key}")
    private String PRODUCTS_KEY;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final RedisTemplate<String,Product> redisTemplate;
    private final ObjectMapper objectMapper;

    public void createProduct(ProductRequestDto productRequestDto) throws InvalidCategoryOrBrandException {

        Optional<Category> category = categoryRepository.findById(productRequestDto.getCategoryId());
        Optional<Brand> brand = brandRepository.findById(productRequestDto.getBrandId());

        if(category.isPresent() && brand.isPresent()){

            Product product = Product.builder()
                    .name(productRequestDto.getName())
                    .availableQuantity(productRequestDto.getQuantity())
                    .pricePerUnit(productRequestDto.getPricePerUnit())
                    .brand(brand.get())
                    .category(category.get())
                    .build();

            productRepository.save(product);

        }else{
            throw new InvalidCategoryOrBrandException("category or brand is not present");
        }
    }

    public List<ProductResponseDto> findAllProducts() throws JsonProcessingException {

        String allProductsJson = (String)redisTemplate.opsForHash().get(PRODUCTS_KEY,"ALL_PRODUCTS");

        System.out.println(allProductsJson);

        if(allProductsJson != null){
            return objectMapper.readValue(allProductsJson, new TypeReference<List<ProductResponseDto>>() {});
        }

        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> productDtos = products.stream().map(ProductResponseDto::from).toList();
        redisTemplate.opsForHash().put(PRODUCTS_KEY,"ALL_PRODUCTS",objectMapper.writeValueAsString(productDtos));

        return productDtos;
    }

    public boolean deleteProduct(long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return true;
        }else{
            return false;
        }
    }

    public Product getProduct(long id) throws JsonProcessingException {

        String productJson =(String) redisTemplate.opsForHash().get(PRODUCTS_KEY, "product_" + id);

        System.out.println(productJson);

        if(productJson != null){
            return objectMapper.readValue(productJson, Product.class);
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            redisTemplate.opsForHash().put(PRODUCTS_KEY, "product_" + id, objectMapper.writeValueAsString(optionalProduct.get()));

            return optionalProduct.get();
        }
        return null;
    }

    public boolean updateProduct(long id, ProductRequestDto productRequestDto) throws InvalidCategoryOrBrandException {


        if(getProductByID(id) != null){

            Optional<Category> category = categoryRepository.findById(productRequestDto.getCategoryId());
            Optional<Brand> brand = brandRepository.findById(productRequestDto.getBrandId());


            if(category.isPresent() && brand.isPresent()){

                Product product = Product.builder()
                        .name(productRequestDto.getName())
                        .availableQuantity(productRequestDto.getQuantity())
                        .pricePerUnit(productRequestDto.getPricePerUnit())
                        .brand(brand.get())
                        .category(category.get())
                        .build();

                productRepository.save(product);

                return true;

            }else{
                throw new InvalidCategoryOrBrandException("category or brand is not present");
            }
        }

        return false;
    }

    @Override
    public List<Product> getProductsByBrand(long brandId) {

        if(brandId > 0 || brandRepository.existsById(brandId)){
            return productRepository.findByBrandId(brandId);
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategory(long categoryId) {

        if(categoryId > 0 || categoryRepository.existsById(categoryId)){
            return productRepository.findByCategoryId(categoryId);
        }

        return null;
    }

    @Override
    public Product getProductByID(long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        return optionalProduct.orElse(null);

    }

}
