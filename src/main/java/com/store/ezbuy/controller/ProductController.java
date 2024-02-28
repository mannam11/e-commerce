package com.store.ezbuy.controller;

import com.store.ezbuy.dto.ProductDto;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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

        return new ResponseEntity<>("Product with id : "+productId+" is not present", HttpStatus.BAD_REQUEST);
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
}