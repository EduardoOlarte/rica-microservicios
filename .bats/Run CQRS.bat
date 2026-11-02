
@echo off
cd /d C:\Users\eduar\Documents\TempProyects\rica-microservicios\consultas-service

echo Configurando Java 25...
set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.4.1"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo Verificando version de Java...
java -version

echo.
echo Iniciando consultas-service...
call mvnw.cmd clean spring-boot:run

echo.
echo El servicio ha finalizado.
pause
