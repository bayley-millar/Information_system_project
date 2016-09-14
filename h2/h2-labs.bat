@echo off

:: location of the H2 database files
set dbhome=h:\h2-dbs

:: create dbhome if it doesn't already exist
if not exist %dbhome% mkdir %dbhome%

:: start H2
java -cp h2-1.4.187.jar -Duser.home=%dbhome% -Dh2.baseDir=%dbhome% -Dh2.bindAddress=localhost org.h2.tools.Console -tcp -web -tool

if errorlevel 1 pause