Feature:Login feature

Scenario:Login using valid Credentials
Given I open HRIS Application 
When I enter valid credentials
Then assertion for time sheet page 


Scenario:Login using Invalid Credentials
Given I open HRIS Application 
When I enter invalid credentials
Then assertion for invalid login text 


Scenario:Login without Credentials
Given I open HRIS Application 
When I do not enter Password
Then assertion for style attribute to be red

