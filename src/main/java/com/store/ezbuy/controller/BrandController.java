package com.store.ezbuy.controller;

import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/add-brand")
    public ResponseEntity<String> addBrand (@RequestBody Brand brand){

        boolean isCreated = brandService.createBrand(brand);
        if(isCreated){
            return new ResponseEntity<>("A new brand added successfully",
                    HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<String> delete(@PathVariable long brandId){
        if(brandService.findByBrandId(brandId).isPresent()){
            brandService.delete(brandId);
            return new ResponseEntity<>("Brand with id: "+brandId +"deleted successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{brandId}")
    public ResponseEntity<String> update (@PathVariable long brandId,
                                          @RequestBody Brand brand){
        boolean isUpdated = brandService.updateBrand(brandId,brand);

        if(isUpdated){
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{brandId}")
    public ResponseEntity<Brand> getBrandById(@PathVariable long brandId){
        if(brandService.findByBrandId(brandId).isPresent()){
            return new ResponseEntity<>(brandService.findByBrandId(brandId).get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Brand>> getAll(){
        return new ResponseEntity<>(brandService.findAll(),HttpStatus.OK);
    }

}
