@addToCart
Feature: Add to Cart functionality

  Background:
    Given user open Amazon homePage

  @browser
  Scenario Outline: 1 Adding a “Monitor” Item in Cart and verifying sub total
    When user enters "<searchItem>" in the searchBox
    And clicks on search submit button
    And selects the "<itemNum>" item in the result list
    And adds selected "<searchItem>" into cart
    And user opens cart menu
    Then verify that item's productPage price should match with cartPage price
    And verify that product page subtotal should match with cart page subtotal
    Examples:
      | searchItem | itemNum |
      | Monitor    | 1st     |

  @browser
  Scenario Outline: 2 Adding a “Laptop” Item in Cart and verifying sub total
    When user enters "<searchItem>" in the searchBox
    And clicks on search submit button
    And selects the "<itemNum>" item in the result list
    And adds selected "<searchItem>" into cart
    And user opens cart menu
    Then verify that item's productPage price should match with cartPage price
    And verify that product page subtotal should match with cart page subtotal
    Examples:
      | searchItem | itemNum |
      | Laptop     | 2nd     |

  @browser
  Scenario Outline: 3 Adding two items in Cart and verifying sub total
    When user enters "<searchItem>" in the searchBox
    And clicks on search submit button
    And selects the "<itemNum>" item in the result list
    And adds selected "<searchItem>" into cart
    When user enters "<searchItem1>" in the searchBox
    And clicks on search submit button
    And selects the "<itemNum1>" item in the result list
    And adds selected "<searchItem1>" into cart
    And user opens cart menu for multiple items added
    Then verify that for each item productPage price should match with cartPage price
    And verify that for each item product page subtotal should match with cart page subtotal
    Examples:
      | searchItem | searchItem1 | itemNum | itemNum1     |
      | Headphones | Keyboard    | 1st     | 1st-keyboard |