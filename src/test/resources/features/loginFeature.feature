Feature: Login to at and capture error message for failure
  @Reg
  Scenario Outline: Login to at and capture error message for failure
    Given I'm on login screen of at using "<browser>"
    When I enter username "<user>", password "<pass>" and submit
    Then If i get error message, capture it.
    Examples:
      |browser            | user              | pass       |
      |chrome             |sreerjvs@gmail     | sreerjvs85 |
      |chrome             |sree21@yahoo       | sree2119   |
      |chrome             |sreerjvs@gmail.com | R@vijaya7  |

  @Reg
  Scenario: Login to at in firefox browser with valid login credentials to check the balance.
    Given I'm on login screen of at using "firefox"
    When I enter username "sreerjvs@gmail.com", password "R@vijaya7" and submit
    Then I should be able to see my login and check "$28.70" balance

  @Reg
  Scenario: Login to at in firefox browser with valid login credentials to check the transactions
    Given I'm on login screen of at using "firefox"
    When I enter username "sreerjvs@gmail.com", password "R@vijaya7" and submit
    Then I click on View Transactions button to see all my previous travels
    And Verify the last transaction's destination as "Tag off : Parnell"

  @New
  Scenario Outline: Login to at in chrome browser with valid login credentials to check the transactions
    Given I'm on login screen of at using "chrome"
    When I enter username "sreerjvs@gmail.com", password "R@vijaya7" and submit
    Then I click on View Transactions button to see all my previous travels
    And Verify the "<transaction>" transaction details like tag on, tag off and hop balance
    Examples:
      | transaction |
      | first       |
      | second      |
      | third       |
      | fourth      |
      | fifth       |