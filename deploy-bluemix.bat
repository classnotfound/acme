@echo off
echo you must be logged with your IBM id before launhing this script!
echo usage:
echo        cf api https://api.eu-gb.bluemix.net
pause
call C:\applications\apache-maven-3.3.9\bin\mvn package

echo Press enter if you are ready to deploy to bluemix
pause
cf push classnotfound -p target/boot-0.0.1-SNAPSHOT.jar

echo access with url: http://classnotfound.eu-gb.mybluemix.net/
