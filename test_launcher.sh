#!/bin/bash
> restest.txt
for fichier in `ls test -a | cut -d "." -f 1`
do
	echo Doing $fichier ...
	echo ---------------------------------- >> restest.txt
	java -jar yal.jar test/$fichier.yal  2>> restest.txt
	java -jar Mars4_5.jar dec v1 $fichier.mips >> restest.txt
done
echo FINISHED

