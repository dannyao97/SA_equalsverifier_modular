#!/bin/bash
set -e

#Java executable for standard Linux environment
export JAVAC=javac
export JAR=jar
#Java executable for MinGW environment
#export JAVAC=/c/jdk9/bin/javac.exe
#export JAR=/c/jdk9/bin/jar.exe

echo "--- COMPILATION & PACKAGING ---"

echo " > creating clean directories"
rm -rf classes
mkdir classes
rm -rf mods
mkdir mods

echo " > multi-compiling modules"
# spark is required as an automatic module, so copy it to mods
cp libs/spark-core-* mods/spark.core.jar
$JAVAC \
	--module-path mods \
	--module-source-path "./*/src/main/java" \
	-d classes \
	--module equalsverifier

echo " > packaging modules"
$JAR --create \
	--file mods/equalsverifier.checkers.jar \
	-C classes/equalsverifier.checkers .
$JAR --create \
	--file mods/equalsverifier.checkers.fieldchecks.jar \
	-C classes/equalsverifier.checkers.fieldchecks .
$JAR --create \
	--file mods/equalsverifier.checkers.beta.jar \
	-C classes/equalsverifier.checkers.beta .
$JAR --create \
	--file mods/equalsverifier.statistics.jar \
	-C classes/equalsverifier.statistics .
$JAR --create \
	--file mods/equalsverifier.prefabvalues.jar \
	-C classes/equalsverifier.prefabvalues .
$JAR --create \
	--file mods/equalsverifier.rest.jar \
	-C classes/equalsverifier.rest .
$JAR --create \
	--file mods/equalsverifier.jar \
	--main-class equalsverifier.Main \
	-C classes/equalsverifier .
