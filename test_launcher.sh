#!/bin/bash
> restest.txt
rm test/*.mips 
for fichier in `ls test -a | cut -d "." -f 1 | grep -v /`
do
	echo Doing $fichier ...
	echo ---------------$fichier------------------- >> restest.txt
	java -jar yal.jar test/$fichier.yal  2>> restest.txt
	java -jar Mars4_5.jar dec v1 test/$fichier.mips >> restest.txt
done
echo FINISHED

