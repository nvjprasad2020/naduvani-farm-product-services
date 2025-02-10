package com.naduvani.farm.product.model.dto;

import com.naduvani.farm.product.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto implements Serializable {
    private String productId;
    private String name;
    private float price;
    private Category category;
}
