Feature: Contact Creation

Background:

Given user is on login page
When user enters valid userid and password
And  clicks on login button

Scenario: Contact_Creation_TC04
When user click on new contact link
And user enters last name,phone number and click on save button
Then contact gets created successfully and able to search using search functionality
And close browser