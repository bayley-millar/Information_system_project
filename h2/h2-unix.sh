#!/bin/bash

# location of H2 database files
dbhome="${HOME}/h2-dbs"

# escape spaces in home folder name
dbhome=$(echo "$dbhome" | tr " " "\\ ")

# create dbhome if it doesn't already exist
if [ ! -d "$dbhome" ]; then mkdir "$dbhome"; fi

# start H2
java -cp $(dirname '$0')/h2-1.4.187.jar -Duser.home="$dbhome" -Dh2.baseDir="$dbhome" -Dh2.bindAddress=localhost org.h2.tools.Console -tcp -web -tool

