# selenium-hello-world
selenium hello world

One time Command to download selenium standalone driver and chromedriver
$./setup.sh

In first command window
$./start-hub.sh

In second command window
$./start-chrome-node.sh

In third command window 
To run using local ChromeDriver
$./mvnw clean test -Dwebdriver.chrome.driver=./chromedriver

To run using remote web driver
$./mvnw clean test -DuseRemoteDriver
