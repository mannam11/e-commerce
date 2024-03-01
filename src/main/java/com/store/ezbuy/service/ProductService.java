package com.store.ezbuy.service;

import com.store.ezbuy.dto.ProductDto;
import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.entity.Category;
import com.store.ezbuy.entity.Product;
import com.store.ezbuy.repository.BrandRepository;
import com.store.ezbuy.repository.CategoryRepository;
import com.store.ezbuy.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    public void createProduct(ProductDto productDto){

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPricePerUnit(productDto.getPricePerUnit());
        product.setQuantity(productDto.getQuantity());

        Optional<Category> optionalDtoCategory = categoryRepository.findByName(productDto.getCategory());
        if(optionalDtoCategory.isEmpty()){
            Category category = new Category();
            category.setName(productDto.getCategory());
            categoryRepository.save(category);
            product.setCategory(category);
        }else{
            product.setCategory(optionalDtoCategory.get());
        }

        Optional<Brand> optionalDtoBrand = brandRepository.findByName(productDto.getBrand());
        if(optionalDtoBrand.isEmpty()){
            Brand brand = new Brand();
            brand.setName(productDto.getBrand());
            brandRepository.save(brand);
            product.setBrand(brand);
        }else{
            product.setBrand(optionalDtoBrand.get());
        }

        productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
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

    public Optional<Product> getProduct(long id){
        return productRepository.findById(id);
    }

    public boolean updateProduct(long id, ProductDto productDto){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();

            existingProduct.setName(productDto.getName());
            existingProduct.setPricePerUnit(productDto.getPricePerUnit());
            existingProduct.setQuantity(productDto.getQuantity());

            Optional<Brand> optionalDtoBrand = brandRepository.findByName(productDto.getBrand());
            if(optionalDtoBrand.isEmpty()){
                Brand brand = new Brand();
                brand.setName(productDto.getBrand());
                brandRepository.save(brand);
                existingProduct.setBrand(brand);
            }else{
                existingProduct.setBrand(optionalDtoBrand.get());
            }

            Optional<Category> optionalDtoCategory = categoryRepository.findByName(productDto.getCategory());
            if(optionalDtoCategory.isEmpty()){
                Category category = new Category();
                category.setName(productDto.getCategory());
                categoryRepository.save(category);
                existingProduct.setCategory(category);
            }else{
                existingProduct.setCategory(optionalDtoCategory.get());
            }
            productRepository.save(existingProduct);

            return true;
        }

        return false;
    }

    public List<Product> getProductsByBrand(String brandName){

        long brandId = brandRepository.findByName(brandName).get().getId();

        return productRepository.findByBrandId(brandId);
    }

    public List<Product> getProductsByCategory(String categoryName){

        long categoryId = categoryRepository.findByName(categoryName).get().getId();

        return productRepository.findByCategoryId(categoryId);
    }
}
