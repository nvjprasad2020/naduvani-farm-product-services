package com.naduvani.farm.product.model.dto;

import com.naduvani.farm.product.model.entity.Category;
import lombok.Data;


@Data
public class ProductResponseDto {
    private String productId;
    private String name;
    private float price;
    private Category category;
}
