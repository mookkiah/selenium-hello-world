#!/bin/sh
#Download selenium server standalone
curl https://selenium-release.storage.googleapis.com/4.0-beta-2/selenium-server-4.0.0-beta-2.jar -o selenium-server.jar

#Download chromedriver for your operating system
./mvnw exec:java -Dexec.args="chrome" #-Dwdm.chromeDriverVersion=89
./mvnw exec:java -Dexec.args="iexplorer"
./mvnw exec:java -Dexec.args="firefox" # -Dwdm.geckoDriverVersion=0.26.0
./mvnw exec:java -Dexec.args="edge" # -Dwdm.edgeDriverVersion=81.0.410.0

