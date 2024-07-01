package com.store.ezbuy.dto;

import com.store.ezbuy.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandDto {

    private String name;

    public static BrandDto toDto(Brand brand) {
        return BrandDto.builder()
                .name(brand.getName())
                .build();
    }
}
