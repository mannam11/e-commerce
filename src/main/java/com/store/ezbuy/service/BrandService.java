package com.store.ezbuy.service;

import com.store.ezbuy.dto.BrandDto;
import com.store.ezbuy.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    public boolean addBrand(BrandDto brandDto);

    public boolean delete(long id);

    public boolean update(long id,BrandDto brandDto);

    public Brand getBrandById(long id);

    public List<Brand> getAllBrands();

    public Optional<Brand> findByName(String name);

}
