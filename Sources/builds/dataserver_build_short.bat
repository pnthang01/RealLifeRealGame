@echo off

setlocal

set CURRENT_DIR="E:\Workspace\Projects\RealLifeRealGame\Sources"

if not exist "%CURRENT_DIR%\DataServer\DataServer\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\DataServer\"
call mvn clean package

goto :movefile

:movefile
MOVE  "%CURRENT_DIR%\DataServer\DataServer\target\*.war" "%CURRENT_DIR%\builds\"

goto :end

:error
echo The structure of projects are wrong or missing files. Please check again!
timeout /t 10 /nobreak > NUL

:end
echo All buils are success.
timeout /t 5 /nobreak > NUL