//package com.store.ezbuy.service;
//
//import com.store.ezbuy.entity.Brand;
//import com.store.ezbuy.repository.BrandRepository;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.ArgumentMatchers.any;
//
//class BrandServiceTest {
//
//    @InjectMocks
//    private BrandServiceImpl brandService;
//
//    @Mock
//    private BrandRepository brandRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void findByBrandName(){
//        String brandName = "Puma";
//        Brand pumaBrand = new Brand();
//        pumaBrand.setName(brandName);
//
//        Mockito.when(brandRepository.findByName(brandName))
//                .thenReturn(Optional.of(pumaBrand));
//
//
//        Optional<Brand> brand = brandRepository.findByName(brandName);
//
//        assertEquals(brandName,brand.get().getName());
//    }
//
//    @Test
//    public void createAnewBrand(){
//        String brandName = "Puma";
//        Brand pumaBrand = new Brand();
//        pumaBrand.setName(brandName);
//
//        Mockito.when(brandRepository.findByName(brandName))
//                .thenReturn(Optional.empty());
//
//        Mockito.when(brandRepository.save(any(Brand.class)))
//                .thenReturn(pumaBrand);
//
//        boolean result = brandService.createBrand(pumaBrand);
//
//        Assert.assertTrue(result);
//    }
//
//    @Test
//    public void delete_existingBrand(){
//        long brandId =1;
//        String brandName = "Puma";
//        Brand pumaBrand = new Brand();
//        pumaBrand.setName(brandName);
//        pumaBrand.setId(brandId);
//
//        Mockito.doNothing().when(brandRepository).deleteById(brandId);
//
//        brandService.delete(brandId);
//    }
//
//    @Test
//    public void findByBrandId(){
//        long brandId =1;
//        String brandName = "Puma";
//        Brand pumaBrand = new Brand();
//        pumaBrand.setName(brandName);
//        pumaBrand.setId(brandId);
//
//        Mockito.when(brandRepository.findById(brandId))
//                .thenReturn(Optional.of(pumaBrand));
//
//        Optional<Brand> brand = brandService.findByBrandId(brandId);
//
//        assertEquals(Optional.of(pumaBrand), brand);
//    }
//
//    @Test
//    public void listOfAllBrands(){
//
//        List<Brand> expectedBrands = Arrays.asList(
//                new Brand(1, "Brand1"),
//                new Brand(2, "Brand2"),
//                new Brand(3, "Brand3")
//        );
//
//        Mockito.when(brandRepository.findAll())
//                .thenReturn(expectedBrands);
//
//        List<Brand> actualBrands = brandService.findAll();
//
//        assertEquals(expectedBrands, actualBrands);
//    }
//
//    @Test
//    public void updateExistingBrand(){
//        long brandId =1;
//        String brandName = "Puma";
//        Brand existingBrand = new Brand(brandId, brandName);
//
//        Mockito.when(brandRepository.findById(brandId))
//                .thenReturn(Optional.of(existingBrand));
//
//        String updatedBrandName = "Adidas";
//        Brand updatedBrand = new Brand(brandId, updatedBrandName);
//
//        Mockito.when(brandRepository.findByName(updatedBrandName))
//                .thenReturn(Optional.empty());
//
//        Mockito.when(brandRepository.save(any(Brand.class)))
//                .thenReturn(updatedBrand);
//
//        boolean result = brandService.updateBrand(brandId, updatedBrand);
//
//        assertTrue(result);
//
//        assertEquals(updatedBrandName, existingBrand.getName());
//    }
//}