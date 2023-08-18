Feature: To Do List Page
  Scenario: Navigate to To Do List Page
    Given User navigates through To Do List Page
    When  User goes through to do list page should verify the current URL
    Then  User evaluates the existance of to do list web elements

  Scenario: Add new task
    Given User navigates through To Do List Page
    When  User enters a new task
    When  User clicks on Add Task button
    Then  User verifies that the task has been created successfully
#
#  Scenario: Mark task as Completed
#    Given User navigates through To Do List Page
#    When  User enters a new task
#    When  User clicks on Add Task button
#    Then  User verifies that the task has been created successfully
#    When  User clicks on Complete button
#    Then  User verifies that the task exists in completed tasks list
#
#  Scenario: Remove completed tasks
#    Given User navigates through To Do List Page
#    When  User enters a new task
#    When  User clicks on Add Task button
#    Then  User verifies that the task has been created successfully
#    When  User clicks on Complete button
#    Then  User verifies that the task exists in completed tasks list
#    When  User clicks on Remove button
#    Then  User verifies that the task does not exist in completed tasks list