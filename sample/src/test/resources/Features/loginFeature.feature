Feature: Login to at and capture error message for failure
  Scenario: Login to at and capture error message for failure
    Given I'm on login screen of at
    When I enter username "sreerjvs@gmail.com", password "sreerjvs85" and submit
    Then If i get error message, capture it.
