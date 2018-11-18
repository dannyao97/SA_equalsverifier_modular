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

echo " > creating equalsverifier.exceptions"
dir /S /B equalsverifier.exceptions\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.exceptions @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.exceptions.jar -C classes/equalsverifier.exceptions .

echo " > creating equalsverifier.prefabvalues"
dir /S /B equalsverifier.prefabvalues\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.prefabvalues @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.prefabvalues.jar -C classes/equalsverifier.prefabvalues.

echo " > creating equalsverifier.reflection"
dir /S /B equalsverifier.reflection\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.reflection @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.reflection.jar -C classes/equalsverifier.reflection.

echo " > creating equalsverifier.service"
rem spark is required as an automatic module, so copy it to mods
copy /y libs\spark-core-* mods\
ren mods\spark-core-*.jar spark.core.jar
dir /S /B equalsverifier.service\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.service @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.service.jar -C classes/equalsverifier.service .

echo " > creating equalsverifier.utils"
dir /S /B equalsverifier.utils\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier.utils @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.utils.jar -C classes/equalsverifier.utils.

echo " > creating equalsverifier"
dir /S /B equalsverifier\src\*.java > sources.txt
javac --module-path mods -d classes/equalsverifier @sources.txt
del sources.txt
jar --create --file mods/equalsverifier.jar --main-class equalsverifier.equalsverifier -C classes/equalsverifier .
