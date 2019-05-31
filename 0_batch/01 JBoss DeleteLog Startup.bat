@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-12
set JBOSS_HOME=c:\tools\jboss-eap-7.1

del /F /Q %JBOSS_HOME%\standalone\log\*
del /F /Q %JBOSS_HOME%\standalone\tmp\*
call %JBOSS_HOME%\bin\standalone.bat -c standalone-full.xml