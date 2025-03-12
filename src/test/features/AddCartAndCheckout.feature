Feature: Add to Cart and Checkout Functionality
  As a user, I want to add items to my cart, proceed to checkout, and complete my order, so that I can purchase products successfully.

  Scenario: User completes the checkout process successfully
    Given User is logged In
    When User clicks on add to cart button
    And User clicks on Cart
    And User proceeds to checkout
    And User fills out the checkout form with first name "John", last name "Doe", and postal code "12345"
    And User clicks continue on the checkout screen
    And User completes the order
    Then Order confirmation should be displayed

  Scenario: User attempts to checkout without filling out the form
    Given User is logged In
    When User clicks on add to cart button
    And User clicks on Cart
    And User attempts to checkout without filling out the form
    Then An error message should be displayed