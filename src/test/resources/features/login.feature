Feature: Login Page
  Scenario: Navigate to Login Page
    Given User navigates through Login Page
    When  User goes through login page should verify the current URL
    Then  User evaluates the existance of login web elements

  Scenario: Sign in with valid credentials
    Given User navigates through Login Page
    When  User fills in the email field
    When  User fills in the password field
    And   User clicks the sign in button
    Then  User verifies the success login message

  Scenario: Verify that user is not logged in if email input is invalid
    Given User navigates through Login Page
    When  User fills in the password field
    And   User clicks the sign in button
    Then  User verifies the email validation error message

  Scenario: Verify that user is not logged in if password input is invalid
    Given User navigates through Login Page
    When  User fills in the email field
    And   User clicks the sign in button
    Then  User verifies the password validation error message