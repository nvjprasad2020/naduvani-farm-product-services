package com.naduvani.farm.product.services;

import com.naduvani.farm.product.exception.ProductNotFoundException;
import com.naduvani.farm.product.model.dto.ProductRequestDto;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Slf4j
public class
ProductServices {

    private final ProductRepository repository;

    @Cacheable(value = "productCache", key = "#productId", unless = "#result == null")
    public ProductResponseDto getProductById(String productId) {
        log.info("Fetching from Database: " + productId);
        Product product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getCategory());
    }

    public List<ProductResponseDto> getAllProducts() {
        return repository.findAll().stream()
                .map(p -> new ProductResponseDto(
                        p.getProductId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCategory()))
                .toList();
    }

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product p = new Product(
                requestDto.getProductId(),
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getCategory());
        Product product = repository.save(p);
        ProductResponseDto responseDto =  new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getCategory());
        log.info("Adding product to cache: {}", responseDto.getProductId() );
        return cacheProduct(responseDto);
    }

    @CacheEvict(value = "productCache", allEntries = true)
    public void deleteAllProduct() {
        repository.deleteAll();
    }

    @CacheEvict(value = "productCache", key = "#productId", condition = "#productId != null")
    public void deleteProductById(String productId) {
        repository.deleteAll();
    }
    @CachePut(value = "productCache", key = "#response.productId")
    public ProductResponseDto cacheProduct(ProductResponseDto response) {
        return response;
    }
}
