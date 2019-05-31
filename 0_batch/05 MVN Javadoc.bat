@echo off
:: 'jaxws-maven-plugin' GOES ONLY WITH JAVA 8  !!!
:: 'javadoc' on Java 8 because the project was build with Java 8
set JAVA_HOME=c:\progra~1\java\jdk18~1.0_1
set M2_HOME=c:\tools\apache-maven-3.6.0
pushd %cd%
cd ..
call %M2_HOME%\bin\mvn dependency:tree
call %M2_HOME%\bin\mvn dependency:resolve -Dclassifier=javadoc
call %M2_HOME%\bin\mvn dependency:resolve -Dclassifier=sources
call %M2_HOME%\bin\mvn javadoc:aggregate
pause
popd