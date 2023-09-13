Feature: Change Of Theme

Scenario: Change_of_theme_TC05

Given user is on login page
When user enters valid userid and password
And user selects nature from theme dropdown & clicks on login button
Then user should be navigated to Home page and clicks logout
And user can see nature in theme dropdown
And close browser