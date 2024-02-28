package com.store.ezbuy.controller;

import com.store.ezbuy.entity.Category;
import com.store.ezbuy.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory (@RequestBody Category category){

        boolean isCreated = categoryService.createCategory(category);
        if(isCreated){
            return new ResponseEntity<>("A new category added successfully", HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> delete(@PathVariable long categoryId){
        if(categoryService.findByCategoryId(categoryId).isPresent()){
            categoryService.delete(categoryId);
            return new ResponseEntity<>("Category with id: "+ categoryId +" deleted successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<String> update (@PathVariable long categoryId,
                                          @RequestBody Category category){
        boolean isUpdated = categoryService.updateCategory(categoryId,category);

        if(isUpdated){
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long categoryId){
        if(categoryService.findByCategoryId(categoryId).isPresent()){
            return new ResponseEntity<>(categoryService.findByCategoryId(categoryId).get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Category>> getAll(){
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }

}
