package com.store.ezbuy.controller;

import com.store.ezbuy.dto.ProductDto;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.service.BrandService;
import com.store.ezbuy.service.CategoryService;
import com.store.ezbuy.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, BrandService brandService, CategoryService categoryService) {
        this.productService = productService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        productService.createProduct(productDto);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable long productId){
        if(productService.deleteProduct(productId)) return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Prodcut with id : "+productId+" is not present", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable long productId){
        if(productService.getProduct(productId).isPresent()){
            return new ResponseEntity<>(productService.getProduct(productId).get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable long productId,
                                                @RequestBody ProductDto productDto){

        if(productService.updateProduct(productId,productDto)){
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Prodcut with id : "+productId+" is not present", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by-brand")
    public ResponseEntity<List<Product>> getByBrand(@RequestParam String brandName){
        if(brandService.findByName(brandName).isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.getProductsByBrand(brandName), HttpStatus.OK);
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String categoryName){
        if(categoryService.findByName(categoryName).isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.getProductsByCategory(categoryName), HttpStatus.OK);
    }
}