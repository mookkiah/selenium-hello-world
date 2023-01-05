#!/bin/sh
#export SAUCE_USERNAME="your-username"
#export SAUCE_ACCESS_KEY="your-access-key"

# To run all tests locally
#./mvnw clean test

# To run Chrome test locally
#./mvnw clean test -Dtest=SampleTest#testChrome

# To run Safai test locally
#./mvnw clean test -Dtest=SampleTest#testSafari

## To run all tests in local grid
#./mvnw clean test -DhubUrl=http://localhost:4444/wd/hub

# To run all tests in local distributed selenium grid4
./mvnw clean test -DhubUrl=http://localhost:4444/wd/hub -Dtest=SampleTest#testChrome

## To run one test in local distributed selenium grid4
#./mvnw clean test -DhubUrl=http://localhost:4444/ -Dtest=SampleTest#testFirefox


# To run Chrome test in local hub and node based grid
#./mvnw clean test -Dtest=SampleTest#testChrome -DhubUrl=http://localhost:4444/wd/hub

# To run Safari test in local hub and node based grid
#./mvnw clean test -Dtest=SampleTest#testSafari -DhubUrl=http://localhost:4444/wd/hub

# To run Firefox test in remote/container based grid (Need IP or port mapping or port forward)
#./mvnw clean test -Dtest=SampleTest#testFirefox -DhubUrl=http://localhost:4444

# To run Edge test in remote/container based grid (Need IP or port mapping or port forward)
#./mvnw clean test -Dtest=SampleTest#testEdge -DhubUrl=http://localhost:4444


#./mvnw clean test -DhubUrl=https://ondemand.saucelabs.com:443/wd/hub
#./mvnw clean test -Dwebdriver.chrome.driver=./chromedriver -Dwebdriver.ie.driver=./IEDriverServer
