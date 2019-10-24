#!/bin/sh
#Download selenium server standalone
curl https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar -o selenium-server-standalone.jar

#Download chromedriver for your operating system
./mvnw exec:java -Dexec.args="chrome"
./mvnw exec:java -Dexec.args="iexplorer"
./mvnw exec:java -Dexec.args="firefox"

