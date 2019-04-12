#!/bin/sh

java -Dwebdriver.chrome.driver="./chromedriver" -jar selenium-server-standalone.jar -role node -nodeConfig chrome-node-config.json -debug 
