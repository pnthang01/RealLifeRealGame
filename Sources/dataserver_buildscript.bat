@echo off

setlocal

set CURRENT_DIR="D:\project\game\RealLifeRealGame\Sources"

if not exist "%CURRENT_DIR%\Utillities\pom.xml" goto :error
cd "%CURRENT_DIR%\Utillities\"
call mvn clean install 

if not exist "%CURRENT_DIR%\DataServer\DataUtils\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\DataUtils\"
call mvn install clean

if not exist "%CURRENT_DIR%\DataServer\Modules\LanguageAPI\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\Modules\LanguageAPI\"
call mvn install clean

if not exist "%CURRENT_DIR%\DataServer\Modules\PblAPI\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\Modules\PblAPI\"
call mvn install clean

if not exist "%CURRENT_DIR%\DataServer\Modules\ProfileAPI\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\Modules\ProfileAPI\"
call mvn install clean

if not exist "%CURRENT_DIR%\DataServer\Modules\TaskAPI\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\Modules\TaskAPI\"
call mvn install clean

if not exist "%CURRENT_DIR%\pbl-checker-module\pom.xml" goto :error
cd "%CURRENT_DIR%\pbl-checker-module\"
call mvn install clean

if not exist "%CURRENT_DIR%\DataServer\DataServer\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\DataServer\"
call mvn clean package

goto :deploy

:deploy
echo -------------------------------------------------------
echo -------- All builds are completed succesfully. --------
echo ---- The data server will be deployed in 10 seconds. ----
echo -------------------------------------------------------
timeout /t 10 /nobreak > NUL

cd "%CURRENT_DIR%\DataServer\DataServer\target"
call java -jar .\data.war

:error
echo The structure of projects are wrong or missing files. Please check again!
timeout /t 10 /nobreak > NUL