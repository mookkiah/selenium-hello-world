#!/bin/sh
#export SAUCE_USERNAME="your-username"
#export SAUCE_ACCESS_KEY="your-access-key"

# To run all tests locally
#mvn clean test

# To run Chrome test locally
#mvn clean test -Dtest=SampleTest#testChrome

# To run Safai test locally
#mvn clean test -Dtest=SampleTest#testSafari

# To run all tests in local grid
mvn clean test -DhubUrl=http://localhost:4444/wd/hub

# To run Chrome test in local grid
#mvn clean test -Dtest=SampleTest#testChrome -DhubUrl=http://localhost:4444/wd/hub

# To run Safari test in local grid
#mvn clean test -Dtest=SampleTest#testSafari -DhubUrl=http://localhost:4444/wd/hub

# To run Firefox test in local grid
#mvn clean test -Dtest=SampleTest#testFirefox -DhubUrl=http://localhost:4444/wd/hub

#mvn clean test -DhubUrl=https://ondemand.saucelabs.com:443/wd/hub
#mvn clean test -Dwebdriver.chrome.driver=./chromedriver -Dwebdriver.ie.driver=./IEDriverServer
