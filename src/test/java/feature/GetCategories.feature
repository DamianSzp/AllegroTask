Feature: GET Category
  Scenario: Verify GET operation categories by ID
    Given I perform authentication operation
      |grant_type|
      |client_credentials|
    And I perform GET BY ID operation "/sale/categories/{categoryId}"
      |categoryId|
      |709|
    Then I should see categories with ID "709"
    And I perform GET operation for "/sale/categories"
    Then I should see Ids of Allegro categories
    And I perform GET operation for parameters supported by category "/sale/categories/{categoryId}/parameters"
      |categoryId|
      |709|
    Then I should see Id of parameters supported by a category