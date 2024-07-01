package com.store.ezbuy.controller;

import com.store.ezbuy.dto.CategoryDto;
import com.store.ezbuy.entity.Category;
import com.store.ezbuy.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory (@RequestBody CategoryDto categoryDto){

        if(categoryDto == null || categoryDto.getName().trim().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isCreated = categoryService.addCategory(categoryDto);
        if(isCreated){
            return new ResponseEntity<>("A new category added successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> delete(@PathVariable long categoryId){

        if(categoryId <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isDeleted = categoryService.delete(categoryId);

        if(isDeleted){
            return new ResponseEntity<>("Category with id: "+ categoryId +" deleted successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<String> update (@PathVariable long categoryId,
                                          @RequestBody CategoryDto categoryDto){

        if(categoryId <= 0 || categoryDto == null || categoryDto.getName().trim().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isUpdated= categoryService.update(categoryId,categoryDto);

        if(isUpdated){
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long categoryId){
        if(categoryId <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Category category = categoryService.getCategoryById(categoryId);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(CategoryDto.fromCategory(category),HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CategoryDto>> getAll(){

        List<Category> categories = categoryService.getAllCategories();

        if(categories == null || categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CategoryDto> categoryDtos = categories.stream().map(CategoryDto::fromCategory).toList();

        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);

    }

}
