set CURRENT_DIR="E:\Workspace\Projects\RealLifeRealGame\Sources"

if not exist "%CURRENT_DIR%\DataServer\DataServer\pom.xml" goto :error
cd "%CURRENT_DIR%\DataServer\DataServer\"
call mvn clean package

goto :deploy

:deploy
echo -------------------------------------------------------
echo -------- All builds are completed succesfully. --------
echo ---- The data server will be deployed in 2 seconds. ----
echo -------------------------------------------------------
timeout /t 2 /nobreak > NUL

cd "%CURRENT_DIR%\DataServer\DataServer\target"
call java -jar .\data.war


:error
echo The structure of projects are wrong or missing files. Please check again!
timeout /t 10 /nobreak > NUL