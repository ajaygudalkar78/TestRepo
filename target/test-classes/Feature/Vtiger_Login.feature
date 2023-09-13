Feature: Login functionality

Background:
Given user is on login page

Scenario: Invalid_login_TC01

When user enters invalid userid and password
And  clicks on login button
Then user should be navigated to login page
And user can see error message
And close browser

Scenario: valid_login_TC02

When user enters valid userid and password
And  clicks on login button
Then user should be navigated to Home page
And user can see logout link
And close browser
