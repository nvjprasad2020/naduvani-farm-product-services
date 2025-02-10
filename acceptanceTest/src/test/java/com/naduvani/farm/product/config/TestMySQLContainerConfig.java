package com.naduvani.farm.product.config;

import org.testcontainers.containers.MySQLContainer;

public class TestMySQLContainerConfig {
    private static final MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");
    static {
        mysqlContainer.start();
    }
}
