Feature: Product Service

  Scenario: Retrieve a product by ID successfully
    Given a product with id "PRD_008", name "Snake Plant", price 12 and category "PLANT_INDOOR" exists
    When I retrieve the product by id "PRD_008"
    Then I should receive a product with id "PRD_008", name "Snake Plant", price 12 and category "PLANT_INDOOR"
