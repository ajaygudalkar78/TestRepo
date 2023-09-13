Feature: Customer Portal Link

Scenario: Verify_customer_portal_link_TC06

Given user is on login page
When user clicks on vtiger Customer Portal link
Then user should be navigated to customer portal in new window
And close browser