Feature: Purchase an order from the Ecommerce Website

  Background:
    Given I landed on the Ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting an order
    Given Logged in with userName "<name>" and password "<password>"
    When I add product "<productName>" to the cart
    And Checkout "<productName>" and submit the order
    Then Message is displayed on the confirmation page

    Examples:
      | name                   | password   | productName |
      | saikavati197@gmail.com | @Bunny1436 | ZARA COAT 3 |
