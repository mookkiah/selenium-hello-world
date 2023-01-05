set SAUCE_USERNAME="your-username"
set SAUCE_ACCESS_KEY="your-access-key"

rem mvn clean test
rem mvn clean test -DhubUrl=http://localhost:4444/wd/hub

REM To run all tests locally
REM mvnw clean test -Dwebdriver.chrome.driver=chromedriver.exe  -Dwebdriver.ie.driver=IEDriverServer.exe -Dwebdriver.gecko.driver=geckodriver.exe


REM To run Firefox test locally
mvnw clean test -Dtest=SampleTest#testFirefox -Dwebdriver.gecko.driver=geckodriver.exe

#export DOCKER_HOST=mm-lab
