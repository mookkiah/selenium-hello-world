#!/bin/sh
mvn clean test -DuseRemoteDriver -Dapp.home=https://google.com -DhubUrl=http://localhost:4444/wd/hub
#mvn clean test -Dwebdriver.chrome.driver=./chromedriver
