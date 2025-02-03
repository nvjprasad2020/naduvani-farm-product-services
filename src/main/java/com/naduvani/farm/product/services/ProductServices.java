package com.naduvani.farm.product.services;

import com.naduvani.farm.product.exception.ProductNotFoundException;
import com.naduvani.farm.product.model.dto.ProductRequestDto;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProductResponseDto> getAllProducts() {
        return repository.findAll().stream()
                .map(p -> new ProductResponseDto(
                        p.getProductId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCategory())).toList();
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product p = new Product(requestDto.getProductId(),
                requestDto.getName(), requestDto.getPrice(), requestDto.getCategory());
        Product product = repository.save(p);
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }
}
