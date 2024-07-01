package com.store.ezbuy.service;

import com.store.ezbuy.dto.CategoryDto;
import com.store.ezbuy.entity.Category;
import com.store.ezbuy.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public boolean addCategory(CategoryDto categoryDto) {

        Optional<Category> existingCategory = findByName(categoryDto.getName());
        if (existingCategory.isPresent()) {
            return false;
        }

        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();

        categoryRepository.save(category);

        return true;
    }

    @Override
    public boolean delete(long id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(long id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDto.getName());
            categoryRepository.save(category);

            return true;
        }

        return false;
    }

    @Override
    public Category getCategoryById(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
