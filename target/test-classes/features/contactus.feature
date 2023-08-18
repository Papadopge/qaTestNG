Feature: Contact Us Page
  Scenario: Navigate to Contact Us Page
    Given User navigates through Contact Us Page
    When  User goes through contact us page should verify the current URL
    Then  User evaluates the existance of contact us web elements

  Scenario: Submit the contact us form with valid data
    Given User navigates through Contact Us Page
    When  User fills in the first name field
    When  User fills in the last name field
    When  User fills in the email field in form
    When  User fills in the password field in form
    When  User fills in the comments field
    And   User clicks the submit button
    Then  User verifies the success submission message

  Scenario: Verify that user cannot submit if required fields are invalid
    Given User navigates through Contact Us Page
    When  User fills in the comments field
    And   User clicks the submit button
    Then  User verifies all the validation error messages for the first name, last name, email and password fields