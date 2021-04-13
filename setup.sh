#!/bin/sh
#Download selenium server standalone
curl https://selenium-release.storage.googleapis.com/4.0-beta-2/selenium-server-4.0.0-beta-2.jar -o selenium-server.jar

#Download chromedriver for your operating system
./mvnw exec:java -Dexec.args="chrome"
./mvnw exec:java -Dexec.args="iexplorer"
./mvnw exec:java -Dexec.args="firefox"

