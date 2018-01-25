REM Ne pas oublier de mettre a jour son yal.jar pour les tests

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/0_0_Constante.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/0_Addition.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/2_Multiplication.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/3_Division.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/4_Greater.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/5_Smaller.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/6_Equals.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/0_O_Constante.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/7_Different.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo ---------------------------------- >> restest.txt
java -jar yal.jar test/8_And.yal 2>> restest.txt
java -jar Mars4_5.jar dec v1 yal.mips >> restest.txt

echo FINISHED
pause

