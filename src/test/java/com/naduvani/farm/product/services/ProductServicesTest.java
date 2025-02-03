package com.naduvani.farm.product.services;

import com.naduvani.farm.product.exception.ProductNotFoundException;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Category;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServicesTest {

    @Test
    void givenProductId_whenGetProductById_thenReturnProductResponseDto() {
        String validProductId = "PRD_001";
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        Product expectedProduct = new Product(validProductId, "Snake Plant", 12, Category.PLANT_INDOOR);
        when(repository.findById(validProductId))
                .thenReturn(Optional.of(expectedProduct));
        ProductServices productServices = new ProductServices(repository);
        ProductResponseDto result = productServices.getProductById(validProductId);
        assertNotNull(result, "The result should not be null");
        assertEquals(validProductId, result.getProductId(), "The product ID should match");
        assertEquals("Snake Plant", result.getName(), "The product name should match");
        assertEquals(12, result.getPrice(), "The product price should match");
        assertEquals(Category.PLANT_INDOOR, result.getCategory(), "The product category should match");
    }

    @Test
    void givenInvalidProductId_whenGetProductById_thenThrowProductNotFoundException() {
        String INVALID_PRODUCT_ID = "PROD_INVALID_ID";
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        when(repository.findById(INVALID_PRODUCT_ID)).thenReturn(Optional.empty());
        ProductServices productServices = new ProductServices(repository);
        assertThrows(ProductNotFoundException.class, () -> productServices.getProductById(INVALID_PRODUCT_ID));
    }

}