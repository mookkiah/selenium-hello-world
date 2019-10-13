#!/bin/sh
mvn clean test -DuseRemoteDriver
#mvn clean test -DuseRemoteDriver -DhubUrl=http://localhost:4444/wd/hub
#mvn clean test -Dwebdriver.chrome.driver=./chromedriver
