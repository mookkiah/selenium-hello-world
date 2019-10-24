set path=%path%;%JAVA_HOME%\bin


rem Download chromedriver for your operating system
./mvnw exec:java -Dexec.args="chrome"
./mvnw exec:java -Dexec.args="iexplorer"
./mvnw exec:java -Dexec.args="firefox"
./mvnw exec:java -Dexec.args="edge"