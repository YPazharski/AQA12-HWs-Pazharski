Feature: I as user can do interactions on main page and get expected results

  Background: Authorized user
    Given I am authorized
    And I am on the Main Page

  Scenario: User remains on the main page after refreshing it
    When I refresh Page
    Then I am on the Main Page

  Scenario: Clicking on Sign Out button gets user to Login Page
    When I click Sign out
    Then I am on the Login Page

  Scenario: Clicking on Privacy Policy on the main page footer gets user to privacy policy page
    When I click on Privacy policy
    Then I am on the Privacy policy page