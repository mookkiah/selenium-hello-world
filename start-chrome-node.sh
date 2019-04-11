#!/bin/sh

java -Dwebdriver.chrome.driver="./chromedriver" -jar selenium-server-standalone.jar -role webdriver -nodeConfig chrome-node-config.json -debug 
