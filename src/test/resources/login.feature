Feature: Login Functionality
  In order to do internet banking
  As a valid Para Bank customer
  I want to login successfully

#  Scenario: Login Successful
#    Given I am in the login page of the Para Bank Application
#    When I enter valid credentials
#    Then I should be taken to the Overview page

#  Scenario: Login Successful
#    Given I am in the login page
#    When I enter valid credentials
#    Then I should be taken to the Overview page

#  Scenario: Login Successful
#    Given I am in the login page
#    When I enter valid credentials
#    |fahmi|
#    |joss123|
#    Then Invalid Credential Warnings

  Scenario Outline: Login Successful
    Given I am in the login page
    When I enter valid <username> and <password> with <userFullName>
#    Then Invalid Credential Warnings
    Then I should be taken to the Overview page

    Examples:
    |username|password|userFullName|
    |"fahmi"|"joss123"|"CHANDAN PAI"|
    |"tautester"|"password"|"CHANDAN PAI"|