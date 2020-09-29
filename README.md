Browser Matrix

[![Sauce Test Status](https://saucelabs.com/browser-matrix/mookkiah.svg)](https://saucelabs.com/u/mookkiah)

# selenium-hello-world

selenium hello world

To run a sample test using Chrome locally.

```
mvn clean test -Dtest=SampleTest#testChrome
```

To run a sample test using Chrome in remote hub (running in docker-selenium)

```
docker run -d -p 4444:4444 -p 5901:5900  -v /dev/shm:/dev/shm selenium/standalone-chrome:4.0.0-alpha-7-prerelease-20200921
mvn clean test -Dtest=SampleTest#testChrome -DhubUrl=http://localhost:4444/wd/hub
```

To run a sample test using Firefox locally.

```
mvn clean test -Dtest=SampleTest#testFirefox
```

To run a sample test using Firefox in remote hub (running in docker-selenium)

```
docker run -d -p 4444:4444 -p 5901:5900 -v /dev/shm:/dev/shm selenium/standalone-firefox:4.0.0-alpha-7-prerelease-20200921
mvn clean test -Dtest=SampleTest#testFirefox -DhubUrl=http://localhost:4444/wd/hub
```

To run a sample test using Safari locally.

```
mvn clean test -Dtest=SampleTest#testSafari
```


One time Command to download selenium standalone driver and chromedriver
\$./setup.sh

In first command window
\$./start-hub.sh

In second command window
\$./start-chrome-node.sh

In third command window
To run using local ChromeDriver
\$./run-test.sh

To run using remote web driver
\$./mvnw clean test -DuseRemoteDriver

---

---

Hit count:

[![HitCount](http://hits.dwyl.io/mookkiah/selenium-hello-world.svg)](http://hits.dwyl.io/mookkiah/selenium-hello-world)
