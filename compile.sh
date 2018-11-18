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

echo " > creating equalsverifier.checkers"
$JAVAC \
	-d classes/equalsverifier.checkers \
	$(find equalsverifier.checkers -name '*.java')
$JAR --create \
	--file mods/equalsverifier.checkers.jar \
	-C classes/equalsverifier.checkers .

echo " > creating equalsverifier.checkers.fieldchecks"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.checkers.fieldchecks \
	$(find equalsverifier.checkers.fieldchecks -name '*.java')
$JAR --create \
	--file mods/equalsverifier.checkers.fieldchecks.jar \
	-C classes/equalsverifier.checkers.fieldchecks .


echo " > creating equalsverifier.checkers.beta"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.checkers.beta \
	$(find equalsverifier.checkers.beta -name '*.java')
$JAR --create \
	--file mods/equalsverifier.checkers.beta.jar \
	-C classes/equalsverifier.checkers.beta .


echo " > creating equalsverifier.statistics"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.statistics \
	$(find equalsverifier.statistics -name '*.java')
$JAR --create \
	--file mods/equalsverifier.statistics.jar \
	-C classes/equalsverifier.statistics .

echo " > creating equalsverifier.prefabvalues"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.prefabvalues \
	$(find equalsverifier.prefabvalues -name '*.java')
$JAR --create \
	--file mods/equalsverifier.prefabvalues.jar \
	-C classes/equalsverifier.prefabvalues .

echo " > creating equalsverifier.rest"
# spark is required as an automatic module, so copy it to mods
cp libs/spark-core-* mods/spark.core.jar
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.rest \
	$(find equalsverifier.rest -name '*.java')
$JAR --create \
	--file mods/equalsverifier.rest.jar \
	-C classes/equalsverifier.rest .

echo " > creating equalsverifier"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier \
	$(find equalsverifier -name '*.java')
$JAR --create \
	--file mods/equalsverifier.jar \
	--main-class equalsverifier.Main \
	-C classes/equalsverifier .
