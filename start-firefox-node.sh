#!/bin/sh
java -Dwebdriver.gecko.driver="./geckodriver" -jar selenium-server-standalone.jar -role node -nodeConfig firefox-node-config.json 
