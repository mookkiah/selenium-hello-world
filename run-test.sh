#!/bin/sh
export SAUCE_USERNAME="your-username"
export SAUCE_ACCESS_KEY="your-access-key"

mvn clean test -DhubUrl=http://localhost:4444/wd/hub
#mvn clean test -DhubUrl=https://ondemand.saucelabs.com:443/wd/hub
#mvn clean test -Dwebdriver.chrome.driver=./chromedriver -Dwebdriver.ie.driver=./IEDriverServer
