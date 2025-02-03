package com.naduvani.farm.product.services;

import com.naduvani.farm.product.exception.ProductNotFoundException;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ProductServices {

    private final ProductRepository repository;

    public ProductResponseDto getProductById(String productId) {
        Product product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }
}
