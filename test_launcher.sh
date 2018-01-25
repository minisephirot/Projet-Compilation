#!/bin/bash
for fichier in `ls test | grep ".yal"`
do
	echo ---------------------------------- >> restest.txt
	java -jar yal.jar test/$fichier 2>> restest.txt
	java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt
done
echo FINISHED

