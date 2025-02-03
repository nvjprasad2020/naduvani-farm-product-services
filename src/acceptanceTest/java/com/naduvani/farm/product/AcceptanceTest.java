package com.naduvani.farm.product;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/acceptanceTest/resources/features",
    glue = {"com.naduvani.farm.product.steps"},
    plugin = {"pretty"},
    monochrome = true
)
public class AcceptanceTest {

}