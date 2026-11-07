@echo off

cd /d "%~dp0..\gateway"

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.4.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo Verificando Java...
java -version

echo.
echo Verificando Maven...
call mvnw.cmd -version

echo.
echo Iniciando Gateway...
call mvnw.cmd spring-boot:run

pause