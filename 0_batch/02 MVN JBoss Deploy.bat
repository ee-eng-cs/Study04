@echo off
:: 'jaxws-maven-plugin' GOES ONLY WITH JAVA 8  !!!
set JAVA_HOME=c:\progra~1\java\jdk18~1.0_1
set M2_HOME=c:\tools\apache-maven-3.6.0
pushd %cd%
cd ..
call %M2_HOME%\bin\mvn clean install
call %M2_HOME%\bin\mvn wildfly:deploy
pause
popd