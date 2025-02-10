Feature: Product Management API

  Scenario: Fetch all products
    Given the following products exist in the system:
      | name      | price | category      |
      | Product 1 | 10.99 | PLANT_INDOOR  |
      | Product 2 | 20.99 | PLANT_OUTDOOR |
      | Product 3 | 30.99 | PLANT_INDOOR  |
      | Product 4 | 40.99 | FERTILIZER    |
      | Product 5 | 50.99 | FERTILIZER    |
    When I send a GET request to fetch all products
    Then the response status should be 200
    And the response should contain at least 5 products
    And the response should contain at least the following products:
      | name      | price | category      |
      | Product 1 | 10.99 | PLANT_INDOOR  |
      | Product 2 | 20.99 | PLANT_OUTDOOR |
      | Product 3 | 30.99 | PLANT_INDOOR  |
      | Product 4 | 40.99 | FERTILIZER    |
      | Product 5 | 50.99 | FERTILIZER    |

  Scenario: Fetch a product with product Id.
    Given a product with name "ProductName", price 12.67, and category "PLANT_INDOOR" exists
    When I send a GET request to fetch the product with the generated ID
    Then the response status should be 200
    And the response should contain the product details:
      | name      | price | category     |
      | Product 1 | 10.99 | PLANT_INDOOR |

  Scenario: Fetch a product that does not exist
    When I send a GET request to fetch the product with non existing ID "99999"
    Then the response status should be 404
    And the response should contain "Product not found"
