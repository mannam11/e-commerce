package com.store.ezbuy.controller;

import com.store.ezbuy.dto.BrandDto;
import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.service.BrandService;
import com.store.ezbuy.service.BrandServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/add-brand")
    public ResponseEntity<String> addBrand (@RequestBody BrandDto brandDto){

        if(brandDto == null || brandDto.getName().trim().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isCreated = brandService.addBrand(brandDto);
        if(isCreated){
            return new ResponseEntity<>("A new brand added successfully",
                    HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{brandId}")
    public ResponseEntity<String> delete(@PathVariable long brandId){

        if(brandId <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(brandService.getBrandById(brandId) != null){
            brandService.delete(brandId);
            return new ResponseEntity<>("Brand with id: "+brandId +"deleted successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update/{brandId}")
    public ResponseEntity<String> update (@PathVariable long brandId,
                                          @RequestBody BrandDto brandDto){

        if(brandId <= 0 || brandDto == null || brandDto.getName().trim().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean isUpdated = brandService.update(brandId,brandDto);

        if(isUpdated){
            return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get/{brandId}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable long brandId){

        if(brandId <= 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Brand brand = brandService.getBrandById(brandId);

        if(brand != null){
            return new ResponseEntity<>(BrandDto.toDto(brand),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BrandDto>> getAll(){

        List<Brand> brands = brandService.getAllBrands();

        List<BrandDto> brandDtos = new ArrayList<>();
        if(brands != null){
            for(Brand brand : brands){
                brandDtos.add(BrandDto.toDto(brand));
            }
        }

        return new ResponseEntity<>(brandDtos,HttpStatus.OK);
    }

}
