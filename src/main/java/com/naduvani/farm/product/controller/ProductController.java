package com.naduvani.farm.product.controller;

import com.naduvani.farm.product.model.dto.ProductRequestDto;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServices productServices;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String productId) {
        ProductResponseDto dto = productServices.getProductById(productId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProductById() {
        List<ProductResponseDto> dtos = productServices.getAllProducts();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto)
            throws URISyntaxException {
        ProductResponseDto newDto = productServices.createProduct(dto);
        return ResponseEntity.created(new URI("/")).body(newDto);
    }
}
