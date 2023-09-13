Feature: Leads Creation

Background:

Given user is on login page
When user enters valid userid and password
And  clicks on login button

Scenario Outline: Leads_Creation_TC03
When user click on new lead link
And user enters last name as "<lastname>" and company as "<company>" and click on save button
Then lead should be created successfully
And close browser

Examples:
|lastname|company  |
|modi    |BJP      |
|Gandhi  |congress |
|Thakre  |ShiveSena|