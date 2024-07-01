package com.store.ezbuy.service;

import com.store.ezbuy.dto.BrandDto;
import com.store.ezbuy.entity.Brand;
import com.store.ezbuy.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public boolean addBrand(BrandDto brandDto) {

        Optional<Brand> brand = findByName(brandDto.getName());
        if (brand.isPresent()) {
            return false;
        }

        Brand b = Brand.builder()
                .name(brandDto.getName())
                .build();

        brandRepository.save(b);

        return true;
    }

    @Override
    public boolean delete(long id) {

        if(brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean update(long id, BrandDto brandDto) {

        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()) {
            Brand b = brand.get();
            b.setName(brandDto.getName());
            brandRepository.save(b);

            return true;
        }

        return false;
    }

    @Override
    public Brand getBrandById(long id) {

        Optional<Brand> brand = brandRepository.findById(id);

        return brand.orElse(null);

    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name);
    }
}
