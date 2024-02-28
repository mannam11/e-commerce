package com.store.ezbuy.service;

import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.entity.Category;
import com.store.ezbuy.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByCategoryId(long id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name){
        return categoryRepository.findByName(name);
    }

    public boolean createCategory(Category category){
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName().toLowerCase());
        if(optionalCategory.isEmpty()){
            categoryRepository.save(category);
            return true;
        }

        return false;
    }

    public void delete(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public boolean updateCategory(long categoryId, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isPresent()){
                Category existingCategory= optionalCategory.get();
            if(categoryRepository.findByName(category.getName()).isPresent()){
                return false;
            }
            existingCategory.setName(category.getName());
            categoryRepository.save(existingCategory);
            return true;
        }

        return false;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
