#!/bin/sh
#Download selenium server standalone
curl https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar -o selenium-server-standalone.jar

#Download chromedriver for your operating system
curl https://chromedriver.storage.googleapis.com/73.0.3683.20/chromedriver_linux64.zip -o chromedriver.zip
#curl https://chromedriver.storage.googleapis.com/73.0.3683.20/chromedriver_mac64.zip -o chromedriver.zip
unzip chromedriver.zip
rm chromedriver.zip

#Download geckodriver for your operating system
#curl https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-macos.tar.gz -o geckodriver.tar.gz
#curl https://github.com/mozilla/geckodriver/releases/download/v0.24.0/geckodriver-v0.24.0-linux64.tar.gz -o geckodriver.tar.gz
#tar -xvf geckodriver.tar.gz
