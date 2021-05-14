@echo off

rem Upgrade-21.3-21.4.java
rem
rem Copyright (c) 2012-2021 Rafael Corchuelo.
rem
rem In keeping with the traditional purpose of furthering education and research, it is
rem the policy of the copyright owner to permit non-commercial use and redistribution of
rem this software. It has been tested carefully, but it is not guaranteed for any particular
rem purposes. The copyright owner does not offer any warranties or representations, nor do
rem they accept any liabilities with respect to them.

set "PROJECT=%1"

if "%~1" == "" (set "ERROR=Usage: %0 project-folder" & goto :error)
if not exist "%PROJECT%" (set "ERROR=Couldn't find your project" & goto :error)
if not exist ".\patches" (set "ERROR=Couldn't find folder patches" & goto :error)
if not exist ".\upgrade.exe" (set "ERROR=Couldn't find upgrade.exe" & goto :error)
if not exist ".\sed.exe" (set "ERROR=Couldn't find sed.exe" & goto :error)
if not exist ".\regex2.dll" (set "ERROR=Couldn't find regex2.dll" & goto :error)
if not exist ".\libintl3.dll" (set "ERROR=Couldn't find libintl3.dll" & goto :error)
if not exist ".\libiconv2.dll" (set "ERROR=Couldn't find libiconv2.dll" & goto :error)
if not exist ".\libcharset1.dll" (set "ERROR=Couldn't find libcharset1.dll" & goto :error)

set "BACKUP=%PROJECT%;%date:~6,4%%date:~3,2%%date:~0,2%%time:~0,2%%time:~3,2%%time:~6,2%"

echo.
echo This utility allows to upgrade a project from version 21.3 to version 21.4.
echo It's safe to use it as long as you haven't changed the framework.
echo Project "%PROJECT%" will be backed up as "%BACKUP%" if you proceed.
echo.

choice /n /c yn /t 9999 /d n /m "Type 'y' to confirm or 'n' to abort the upgrade"
if errorlevel 2 (set "ERROR=Aborting upgrade" & goto :error)

echo.
echo Backing up your project.
echo.

xcopy /s /v /y /q /r /h /i "%PROJECT%" "%BACKUP%"

echo.
choice /n /c yn /t 9999 /d n /m "Las opportunity to cancel. Type 'y' to proceed or 'n' to abort"
if errorlevel 2 (set "ERROR=Aborting upgrade" & goto :error)

echo.
echo Upgrading your project.
echo.

echo Patching file "%PROJECT%\pom.xml" 
.\sed.exe -i "1,$ s/<version>21.3<\/version>/<version>21.4<\/version>/g" "%PROJECT%\pom.xml" 
echo Patching file "%PROJECT%\clevercloud\war.json"
.\sed.exe -i "1,$ s/21\.3\.war/21\.4\.war/g" "%PROJECT%\clevercloud\war.json"
echo Patching file "%PROJECT%\CHANGELOG.txt"
.\upgrade.exe --silent "%PROJECT%\CHANGELOG.txt" .\patches\CHANGELOG.txt.patch
echo Patching file "%PROJECT%\src\main\properties\banner.txt"
.\sed.exe -i "1,$ s/Powered by Starter-Project 21\.3/Powered by Starter-Project 21\.4/g" "%PROJECT%\src\main\properties\banner.txt"

echo Copying a few files...
xcopy /v /y .\patches\StringHelper.java "%PROJECT%\src\main\java\acme\framework\helpers\StringHelper.java"
xcopy /v /y /s .\patches\framework-testing\*.* "%PROJECT%\src\main\java\acme\framework\testing\"
xcopy /v /y /s .\patches\sample-testing\*.* "%PROJECT%\src\test\java\acme\testing"

echo.
echo Upgrading finished.  If there were any errors, please consult 
echo the README.txt file and perform a manual upgrade.
echo.

goto :end

:error

	echo.
	echo %ERROR%
	echo.

	goto :end

:end