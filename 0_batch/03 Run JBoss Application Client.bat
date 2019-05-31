@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-12
set JBOSS_HOME=c:\tools\jboss-eap-7.1

del /F /Q %JBOSS_HOME%\appclient\log\*
del /F /Q %JBOSS_HOME%\appclient\tmp\*
call %JBOSS_HOME%\bin\appclient.bat ..\ear\target\Study04.ear#Study04_appclient.jar
pause