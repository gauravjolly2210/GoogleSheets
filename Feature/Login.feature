Feature:Login feature

@ID_01
Scenario:Login using valid Credentials
Given I open HRIS Application 
When I enter valid credentials
Then assertion for time sheet page 

@ID_02
Scenario:Login using Invalid Credentials
Given I open HRIS Application 
When I enter invalid credentials
Then assertion for invalid login text 

@ID_03
Scenario:Login without Credentials
Given I open HRIS Application 
When I do not enter Password
Then assertion for style attribute to be red

