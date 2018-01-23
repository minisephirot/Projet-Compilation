main:
# init variable repérer la zone des variables
move $s7, $sp
# opération  + , calcul de gauche
# constante entière
li $v0, 666
# empiler gauche
sw $v0, ($sp)
addi $sp, $sp, -4
# calcul de droite
# constante entière
li $v0, 12
# dépile droite dans $t8
addi $sp, $sp, 4
lw $t8, ($sp)
# addition gauche droite
add $v0, $v0, $t8
