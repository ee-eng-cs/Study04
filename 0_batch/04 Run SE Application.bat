@echo off
:: 'jaxws-maven-plugin' GOES ONLY WITH JAVA 8  !!!
set JAVA_HOME=c:\progra~1\java\jdk18~1.0_1
set JBOSS_HOME=c:\tools\jboss-eap-7.1
set MAIN_JAR=..\se\target\Study04_se.jar
set LIB_JARS=..\common\target\Study04_common.jar;%JBOSS_HOME%\bin\client\jboss-client.jar 
if not exist %MAIN_JAR% goto end
call %JAVA_HOME%\bin\java -cp %MAIN_JAR%;%LIB_JARS% kp.se.MainEntry
:end
pause
