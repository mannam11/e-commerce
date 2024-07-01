package com.store.ezbuy.service;

import com.store.ezbuy.dto.CategoryDto;
import com.store.ezbuy.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService{

    public boolean addCategory(CategoryDto categoryDto);

    public boolean delete(long id);

    public boolean update(long id,CategoryDto categoryDto);

    public Category getCategoryById(long id);

    public List<Category> getAllCategories();

    public Optional<Category> findByName(String name);

}
