package com.store.ezbuy.dto;

import com.store.ezbuy.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String name;

    public static CategoryDto fromCategory(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }

}
