@echo off

cd /d "%~dp0..\publicaciones-service"

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.4.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo Verificando Java...
java -version

echo.
echo Iniciando Publicaciones Service...
call mvnw.cmd clean spring-boot:run

pause