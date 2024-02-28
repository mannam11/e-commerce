package com.store.ezbuy.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private int quantity;
    private int pricePerUnit;
    private String category;
    private String brand;
}
