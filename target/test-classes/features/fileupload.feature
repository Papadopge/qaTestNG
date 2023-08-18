Feature: File Upload Page
  Scenario: Navigate to File Upload Page
    Given User navigates through File Upload Page
    When  User goes through file upload page should verify the current URL
    Then  User evaluates the existance of file upload web elements

  Scenario: Upload a file
    Given User navigates through File Upload Page
    When  User clicks the Choose File Button
    And   User selects a file from the file explorer
    When  User clicks the upload button
    Then  User verifies the success uploading message

  Scenario: Verify that user cannot upload if no file has been selected
    Given User navigates through File Upload Page
    When  User clicks the upload button
    Then  User verifies that no uploading message exists