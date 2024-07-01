package com.store.ezbuy.dto;


import com.store.ezbuy.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String name;
    private int quantity;
    private int pricePerUnit;
    private String category;
    private String brand;

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .name(product.getName())
                .quantity(product.getAvailableQuantity())
                .pricePerUnit(product.getPricePerUnit())
                .brand(product.getBrand().getName())
                .category(product.getCategory().getName())
                .build();
    }

    public static List<ProductResponseDto> from(List<Product> products){
        return products.stream().map(ProductResponseDto::from).toList();
    }
}
