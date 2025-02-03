package com.naduvani.farm.product.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {
    @Given("a product with id {string}, name {string}, price {int} and category {string} exists")
    public void a_product_with_id_name_price_and_category_exists(String pro, String string2, Integer int1, String string3) {

    }

    @When("I retrieve the product by id {string}")
    public void i_retrieve_the_product_by_id(String string) {
    }

    @Then("I should receive a product with id {string}, name {string}, price {int} and category {string}")
    public void i_should_receive_a_product_with_id_name_price_and_category(String string, String string2, Integer int1, String string3) {

    }

}
