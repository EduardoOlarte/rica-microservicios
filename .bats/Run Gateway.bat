@echo off

cd /d C:\Users\eduar\Documents\TempProyects\rica-microservicios\gateway

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.4.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo Verificando Java...
java -version

echo Verificando Maven...
call mvnw.cmd -version

echo Iniciando Gateway...
call mvnw.cmd spring-boot:run

pause