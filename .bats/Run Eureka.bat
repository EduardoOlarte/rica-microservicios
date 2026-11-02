@echo off

cd /d C:\Users\eduar\Documents\TempProyects\rica-microservicios\eureka-server

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.4.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Verificando Java...
java -version

echo Verificando Maven...
call mvnw.cmd -version

echo Iniciando Eureka Server...
call mvnw.cmd clean spring-boot:run

pause