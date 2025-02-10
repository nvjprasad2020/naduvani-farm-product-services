package com.naduvani.farm.product.controller;

import com.naduvani.farm.product.model.dto.ProductRequestDto;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.services.ProductServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductController {

    private final ProductServices productServices;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String productId) {
        log.info("Fetching product by id {}", productId);
        ProductResponseDto dto = productServices.getProductById(productId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProductById() {
        log.info("Fetching all product");
        List<ProductResponseDto> dtos = productServices.getAllProducts();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto)
            throws URISyntaxException {
        log.info("Creating a new product");
        ProductResponseDto newDto = productServices.createProduct(dto);
        return ResponseEntity.created(new URI("/")).body(newDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts()
            throws URISyntaxException {
        log.info("deleting ALl products");
        productServices.deleteAllProduct();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteAllProducts(@RequestParam String productId)
            throws URISyntaxException {
        log.info("deleting ALl products");
        productServices.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}
