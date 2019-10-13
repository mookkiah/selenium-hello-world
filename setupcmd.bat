set path=%path%;%JAVA_HOME%\bin


#Download chromedriver for your operating system
mvn exec:java -Dexec.args="chrome"
mvn exec:java -Dexec.args="iexplorer"