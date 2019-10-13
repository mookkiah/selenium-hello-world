set path=%path%;%JAVA_HOME%\bin


rem Download chromedriver for your operating system
mvn exec:java -Dexec.args="chrome"
mvn exec:java -Dexec.args="iexplorer"
mvn exec:java -Dexec.args="firefox"
mvn exec:java -Dexec.args="edge"