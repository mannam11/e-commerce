package com.store.ezbuy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.ezbuy.dto.ProductRequestDto;
import com.store.ezbuy.dto.ProductResponseDto;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.exceptions.custom_exceptions.InvalidCategoryOrBrandException;
import com.store.ezbuy.service.BrandServiceImpl;
import com.store.ezbuy.service.CategoryServiceImpl;
import com.store.ezbuy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto productDto) throws InvalidCategoryOrBrandException {

        if(productDto == null || productDto.getName().trim().isEmpty() || productDto.getQuantity() < 0 || productDto.getPricePerUnit() <= 0){
            throw new RuntimeException("Invalid product request");
        }

        productService.createProduct(productDto);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() throws JsonProcessingException {
        return new ResponseEntity<>(productService.findAllProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable long productId){

        if(productId <= 0){
            throw new RuntimeException("Invalid product id");
        }

        if(productService.deleteProduct(productId)) return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Product with id : "+productId+" is not present", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long productId) throws JsonProcessingException {

        if(productId <= 0){
            throw new RuntimeException("Invalid product id");
        }

        Product product = productService.getProduct(productId);
        if(product != null) return new ResponseEntity<>(ProductResponseDto.from(product),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable long productId,
                                                @RequestBody ProductRequestDto productDto) throws InvalidCategoryOrBrandException {

        if(productId <= 0){
            throw new RuntimeException("Invalid product id");
        }

        if(productService.updateProduct(productId,productDto)){
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Product with id : "+productId+" is not present", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-brand/{brandId}")
    public ResponseEntity<List<ProductResponseDto>> getByBrand(@PathVariable long brandId){

        if(brandId <= 0){
            throw new RuntimeException("Invalid brand id");
        }

        List<Product> products = productService.getProductsByBrand(brandId);
        if(products != null){
            return new ResponseEntity<>(ProductResponseDto.from(products),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@PathVariable long categoryId){

        if(categoryId <= 0){
            throw new RuntimeException("Invalid category id");
        }

        List<Product> products = productService.getProductsByCategory(categoryId);
        if(products != null){
            return new ResponseEntity<>(ProductResponseDto.from(products),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}