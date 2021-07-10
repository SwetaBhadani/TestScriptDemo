Feature:

  Background: Setup
    Given I open the website

  Scenario:
    Given I add 4 different products to my wishlist
    When I view my wishlist table
    Then I find total four selected items in my wishlist
    When I search for lowest price product
    And I am able to add lowest price item to my cart
    Then I am able to verify the item in my cart

