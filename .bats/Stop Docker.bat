@echo off

echo.
echo Deteniendo contenedores de Investigadores...
cd /d "%~dp0..\investigadores-service"

docker compose down

echo.
echo Deteniendo contenedores de Publicaciones...
cd /d "%~dp0..\publicaciones-service"

docker compose down

echo.
echo Contenedores detenidos.
pause