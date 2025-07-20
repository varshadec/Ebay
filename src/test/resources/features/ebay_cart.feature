Feature: Adding Book into cart and counting the number
  Scenario: Verify the item in the cart
    Given User navigate to ebay website
    And User search for Book in search
    And Select search button
    And Click on first book
    And Click on Add to cart
    Then User should see successfully added to cart with cart count 