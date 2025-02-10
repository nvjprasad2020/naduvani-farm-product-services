package com.naduvani.farm.product.steps;

import com.naduvani.farm.product.model.dto.ProductRequestDto;
import com.naduvani.farm.product.model.dto.ProductResponseDto;
import com.naduvani.farm.product.model.entity.Category;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductSteps {
    String baseUrl = "http://localhost";
    private static final String PATH_URI = "/api/v1/products";
    private final int port = 8081;
    private Response response;
    private ValidatableResponse validatableResponse;
    private String productId;
    private String createdProductId;

    @BeforeEach
    public void cleanDatabase() {
        given()
                .baseUri(baseUrl)
                .port(port)
                .when()
                .delete(PATH_URI)
                .then()
                .statusCode(204);
    }

    @Given("the following products exist in the system:")
    public void the_following_products_exist_in_the_system(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps();
        products.stream().map(stringStringMap -> new ProductRequestDto(
                        null,
                        stringStringMap.get("name"),
                        Float.parseFloat(stringStringMap.get("price")),
                        Category.PLANT_INDOOR)).toList()
                .forEach(requestDto -> {
                    given()
                            .baseUri(baseUrl)
                            .port(port)
                            .contentType("application/json")
                            .body(requestDto)
                            .when()
                            .post(PATH_URI)
                            .then()
                            .statusCode(201);
                });
    }

    @When("I send a GET request to fetch all products")
    public void i_send_a_get_request_to_fetch_all_products() {
        System.out.println("\nI send a GET request to fetch all products");
        response = given()
                .baseUri(baseUrl)
                .port(port)
                .when()
                .get(PATH_URI);
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        System.out.println("\nthe response status should be " + statusCode);
        assertEquals(statusCode, response.statusCode());
    }

    @Then("the response should contain at least {int} products")
    public void the_response_should_contain_at_least_products(Integer size) {
        ProductResponseDto[] products = response.getBody().as(ProductResponseDto[].class);
        Assertions.assertTrue(products.length >= 5);
    }

    @Then("the response should contain at least the following products:")
    public void the_response_should_contain_at_least_the_following_products(DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps();
        List<ProductResponseDto> expectedProducts =
                products.stream().map(stringStringMap -> new ProductResponseDto(
                        null,
                        stringStringMap.get("name"),
                        Float.parseFloat(stringStringMap.get("price")),
                        Category.valueOf(stringStringMap.get("category")))).toList();

        List<Map<String, Object>> actualProducts = response.jsonPath().getList("$");

        List<ProductResponseDto> actualProductDtos = actualProducts.stream()
                .map(productMap -> new ProductResponseDto(
                        null,
                        (String) productMap.get("name"),
                        ((Number) productMap.get("price")).floatValue(),
                        Category.valueOf((String) productMap.get("category"))
                ))
                .toList();
//        assertThat("The response does not contain the expected products",
//                expectedProducts, containsInAnyOrder(actualProductDtos));
    }


    @Given("a product with name {string}, price {float}, and category {string} exists")
    public void aProductWithNamePriceAndCategoryExists(String name, float price, String category) {
        ProductRequestDto productRequestDto = new ProductRequestDto(null, name, price, Category.valueOf(category));
        ValidatableResponse validatableResponse = given()
                .baseUri(baseUrl)
                .port(port)
                .contentType("application/json")
                .body(productRequestDto)
                .when()
                .post(PATH_URI)
                .then()
                .statusCode(201);
        createdProductId = validatableResponse.extract().path("productId");
    }

    @When("I send a GET request to fetch the product with the generated ID")
    public void i_send_a_get_request_to_fetch_the_product_with_id() {
        response = given()
                .baseUri(baseUrl)
                .port(port)
                .when()
                .get(PATH_URI + "/" + createdProductId);
    }

    @Then("the response should contain the product details:")
    public void the_response_should_contain_the_product_details(io.cucumber.datatable.DataTable dataTable) {
    }

    @When("I send a GET request to fetch the product with non existing ID {string}")
    public void i_send_a_get_request_to_fetch_the_product_with_non_existing_id(String productId) {
        response = given()
                .baseUri(baseUrl)
                .port(port)
                .when()
                .get(PATH_URI + "/" + productId);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String msg) {
    }

}
