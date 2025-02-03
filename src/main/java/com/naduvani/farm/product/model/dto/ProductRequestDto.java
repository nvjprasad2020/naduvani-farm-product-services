package com.naduvani.farm.product.model.dto;

import com.naduvani.farm.product.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String productId;
    private String name;
    private float price;
    private Category category;
}
