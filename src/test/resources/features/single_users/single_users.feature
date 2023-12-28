Feature: Single Users

  Scenario: Get Single Users
    Given User is already get single users with user id "1"
    When User get data from response api single users
    Then User get first name "George"