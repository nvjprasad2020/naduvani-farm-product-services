package com.naduvani.farm.product.steps;

import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Category;
import com.naduvani.farm.product.model.entity.Product;
import com.naduvani.farm.product.repository.ProductRepository;
import com.naduvani.farm.product.services.ProductServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


public class ProductSteps {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;
    private ProductResponseDto retrievedProduct;

    @Given("a product with id {string}, name {string}, price {float} and category {string} exists")
    public void a_product_with_id_name_price_and_category_exists(
            String id, String name, float price, String category) {
        Product product = new Product(id, name, price, Category.valueOf(category));
        productRepository.save(product);
        System.out.println("Product saved: " + product);
    }

    @When("I retrieve the product by id {string}")
    public void i_retrieve_the_product_by_id(String id) {
        retrievedProduct = productServices.getProductById(id);
        Assertions.assertNotNull(retrievedProduct, "Product should not be null");
    }

    @Then("I should receive a product with id {string}, name {string}, price {float} and category {string}")
    public void i_should_receive_a_product_with_id_name_price_and_category(
            String id, String name, float price, String category) {
        System.out.println(" Asserting values fetched via service");
        Assertions.assertEquals(id, retrievedProduct.getProductId());
        Assertions.assertEquals(name, retrievedProduct.getName());
        Assertions.assertEquals(price, retrievedProduct.getPrice(), 0.01);
        Assertions.assertEquals(category, retrievedProduct.getCategory().name());
    }
}
