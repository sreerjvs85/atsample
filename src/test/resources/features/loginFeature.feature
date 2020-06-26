Feature: Login to at and capture error message for failure
  Scenario Outline: Login to at and capture error message for failure
    Given I'm on login screen of at
    When I enter username "<user>", password "<pass>" and submit
    Then If i get error message, capture it.
    Examples:
      | user              | pass       |
      |sreerjvs@gmail     | sreerjvs85 |
      |sree21@yahoo       | sree2119   |
      |sreerjvs@gmail.com | R@vijaya9  |
