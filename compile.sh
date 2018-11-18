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

echo " > creating equalsverifier.exceptions"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.exceptions \
	$(find equalsverifier.exceptions -name '*.java')
$JAR --create \
	--file mods/equalsverifier.exceptions.jar \
	-C classes/equalsverifier.exceptions .

echo " > creating equalsverifier.prefabvalues"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.prefabvalues \
	$(find equalsverifier.prefabvalues -name '*.java')
$JAR --create \
	--file mods/equalsverifier.prefabvalues.jar \
	-C classes/equalsverifier.prefabvalues .

echo " > creating equalsverifier.reflection"
# spark is required as an automatic module, so copy it to mods
cp libs/spark-core-* mods/spark.core.jar
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.reflection \
	$(find equalsverifier.rest -name '*.java')
$JAR --create \
	--file mods/equalsverifier.reflection.jar \
	-C classes/equalsverifier.reflection .

echo " > creating equalsverifier.service"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.service \
	$(find equalsverifier.service -name '*.java')
$JAR --create \
	--file mods/equalsverifier.service.jar \
	-C classes/equalsverifier.service .

echo " > creating equalsverifier.utils"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier.utils \
	$(find equalsverifier.utils -name '*.java')
$JAR --create \
	--file mods/equalsverifier.utils.jar \
	-C classes/equalsverifier.utils.

echo " > creating equalsverifier"
$JAVAC \
	--module-path mods \
	-d classes/equalsverifier \
	$(find equalsverifier -name '*.java')
$JAR --create \
	--file mods/equalsverifier.jar \
	--main-class equalsverifier.equalsverifier \
	-C classes/equalsverifier .
