Feature: App Login

  @UI @Smoke
  Scenario: Login with invalid credentials
    Given I am on the login page
    When I login with username "invalid_user" and password "wrong_pass"
    Then I should see an error message "Invalid credentials"
