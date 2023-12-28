Feature: Create Users

  Scenario: Create Users
    Given User is already create users with name "Ferdy" and job "Quality Assurance"
    When User get data from response api create users
    Then User get name "Ferdy" and job "Quality Assurance" as result