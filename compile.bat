@echo off
echo "--- COMPILATION & PACKAGING ---"

echo " > creating clean directories"
del /s /q classes
rmdir /s /q classes
mkdir classes
del /s /q mods
rmdir /s /q mods
mkdir mods

echo " > creating equalsverifier.checkers"
dir /S /B equalsverifier.checkers\src\*.java > sources.txt
javac -d classes/equalsverifier.checkers @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.checkers.jar -C classes/equalsverifier.checkers .

echo " > creating equalsverifier.checkers.fieldchecks"
dir /S /B equalsverifier.checkers.fieldchecks\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.checkers.fieldchecks @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.checkers.fieldchecks.jar -C classes/equalsverifier.checkers.fieldchecks .

echo " > creating equalsverifier.checkers.beta"
dir /S /B equalsverifier.checkers.beta\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.checkers.beta @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.checkers.beta.jar -C classes/equalsverifier.checkers.beta .

echo " > creating equalsverifier.statistics"
dir /S /B equalsverifier.statistics\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.statistics @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.statistics.jar -C classes/equalsverifier.statistics .

echo " > creating equalsverifier.prefabvalues"
dir /S /B equalsverifier.prefabvalues\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.prefabvalues @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.prefabvalues.jar -C classes/equalsverifier.prefabvalues .

echo " > creating equalsverifier.rest"
rem spark is required as an automatic module, so copy it to mods
copy /y libs\spark-core-* mods\
ren mods\spark-core-*.jar spark.core.jar
dir /S /B equalsverifier.rest\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.rest @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.rest.jar -C classes/equalsverifier.rest .

echo " > creating equalsverifier"
dir /S /B equalsverifier\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.jar --main-class equalsverifier.Main -C classes/equalsverifier .
