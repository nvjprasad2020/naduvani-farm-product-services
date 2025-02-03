package com.naduvani.farm.product;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {TestMySQLContainerConfig.class})
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/acceptanceTest/resources/features")
@CucumberContextConfiguration
@SpringBootTest
public class CucumberTest {
}
