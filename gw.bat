@echo off
setlocal enabledelayedexpansion
set CURR_PATH=%cd%

set REAL_GRADLEW=%CURR_PATH%\gradlew.bat

if exist %REAL_GRADLEW% (
goto :found
)

:while1
call :getdir "%CURR_PATH%"

set REAL_GRADLEW=!CURR_PATH!\gradlew.bat

if exist !REAL_GRADLEW! (
goto :found
)

if "%CURR_PATH:~-1%" == ":" (
goto :notfound
)
goto :while1

:notfound
echo Unable to find gradlew.bat file upwards in filesystem
goto :goodbye

:found
call !REAL_GRADLEW! %*

:goodbye
endlocal
goto :EOF

:getdir
set "CURR_PATH=%~dp1"
set "CURR_PATH=%CURR_PATH:~0,-1%"
