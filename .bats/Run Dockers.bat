@echo off

echo.
echo Reiniciando contenedores de Investigadores...
cd /d "%~dp0..\investigadores-service"

docker compose down
docker compose up -d

echo.
echo Reiniciando contenedores de Publicaciones...
cd /d "%~dp0..\publicaciones-service"

docker compose down
docker compose up -d

echo.
echo Contenedores reiniciados.
pause