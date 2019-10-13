set SAUCE_USERNAME="your-username"
set SAUCE_ACCESS_KEY="your-access-key"

rem mvn clean test -DuseRemoteDriver
rem mvn clean test -DuseRemoteDriver -DhubUrl=http://localhost:4444/wd/hub
mvn clean test -Dwebdriver.chrome.driver=chromedriver.exe  -Dwebdriver.ie.driver=IEDriverServer.exe
