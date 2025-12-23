Feature: User Management API

  @API
  Scenario: Create a new user
    Given I have a valid user payload
    When I send a POST request to create a user
    Then the response status code should be 201
