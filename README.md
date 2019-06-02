# SampleAutomation

Clone the repository using : git clone https://github.com/Harshit321/SampleAutomation.git

To run the test cases directly, go to the folder in which you cloned the repository and run following command

mvn clean test

Browser Supported: chrome, firefox

In SampleAutomation/src/main/java/com/amazon/qa/config/config.properties - user can change username, password and browser.

to change browser, there are 2 values - chrome or firefox

To run testcases parallely, make the following changes to testng.xml

"suite name="Sample Test Automation" thread-count="3" parallel="methods" "
  
Reporting - Extent Report is supported

Extent report is formed at the following path - SampleAutomation/test-output/Results_ExtentReport.html


Also TestNG reporting is enabled by default.
