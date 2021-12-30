REM TODO: if java is not in path
REM set path=%path%;%JAVA_HOME%\bin

REM Download chromedriver for your operating system
mvnw exec:java -Dexec.args="chrome"
mvnw exec:java -Dexec.args="iexplorer"
mvnw exec:java -Dexec.args="firefox"
mvnw exec:java -Dexec.args="edge"