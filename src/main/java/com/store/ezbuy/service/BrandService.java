package com.store.ezbuy.service;

import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Optional<Brand> findByName(String name){
        return brandRepository.findByName(name);
    }

    public boolean createBrand(Brand brand){
        Optional<Brand> optionalBrand = brandRepository.findByName(brand.getName().toLowerCase());
        if(optionalBrand.isEmpty()){
            brandRepository.save(brand);
            return true;
        }

        return false;
    }

    public void delete (long id){
        brandRepository.deleteById(id);
    }

    public Optional<Brand> findByBrandId(long id){
        return brandRepository.findById(id);
    }

    public boolean updateBrand(long id,Brand brand){
        Optional<Brand> optionalBrand = brandRepository.findById(id);

        if(optionalBrand.isPresent()){
            Brand existingBrand= optionalBrand.get();
            if(brandRepository.findByName(brand.getName()).isPresent()){
                return false;
            }
            existingBrand.setName(brand.getName());
            brandRepository.save(existingBrand);
            return true;
        }

        return false;
    }

    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
}
