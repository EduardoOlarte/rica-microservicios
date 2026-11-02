
@echo off
title Iniciar microservicios RICA

set "BAT_DIR=C:\Users\eduar\Documents\TempProyects\rica-microservicios\.bats"


echo       INICIANDO MICROSERVICIOS


start "Docker" cmd /k call "%BAT_DIR%\Run Dockers.bat"
timeout /t 10 /nobreak >nul

start "Eureka" cmd /k call "%BAT_DIR%\Run Eureka.bat"
timeout /t 10 /nobreak >nul

start "Investigadores" cmd /k call "%BAT_DIR%\Run Investigadores.bat"

start "CQRS" cmd /k call "%BAT_DIR%\Run CQRS.bat"

start "Publicaciones" cmd /k call "%BAT_DIR%\Run Publicaciones.bat"

start "Gateway" cmd /k call "%BAT_DIR%\Run Gateway.bat"

echo.
echo Todos los procesos fueron iniciados.

