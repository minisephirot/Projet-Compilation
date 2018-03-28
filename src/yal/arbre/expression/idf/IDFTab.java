package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeTab;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

/**
 * Classe qui permet de charger l'adresse d'un case du tableau dans a0
 * Elle est utilisée par les classe AffectationTab, EcrireTab et ChercherTab
 */
public class IDFTab extends Expression {

        private String nom;
        private Expression indice;
        private int decalage;
        private int noBloc;

        public IDFTab(String idf, Expression indice, int n) {
            super(n);
            this.returnType = "int"; //Toutes nos variables sont des entiers
            nom = idf;
            this.indice = indice;
        }

        @Override
        public void verifierConstante() {
            ListeErreursSemantiques.getInstance().addErreur(noLigne,"La déclaration d'un tableau dans le bloc principal doit se faire sans variable");
        }

        @Override
        public void verifier() {
            // Vérifie que la variable est déclarée
            SymboleVar s = (SymboleVar) TableDesSymboles.getInstance().identifier(new EntreeTab(nom, noLigne));
            if (s != null) {
                decalage = s.getPos();
                noBloc = s.getNoBloc();
            } else {
                decalage = 1; // Décalage faux car la variable n'est pas décalrée
                noBloc = 0;
            }
            indice.verifier();
            if (!indice.getReturnType().equals("int"))
                ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");

        }

        public String getNom() {
            return nom;
        }

        public int getDecalage() {
            return decalage;
        }

        public int getNoBloc() {
            return noBloc;
        }

        @Override
        public String toMIPS() {
            StringBuilder sb = new StringBuilder();
            sb.append("# Evaluation de l'indice \n");
            sb.append(indice.toMIPS());

            /**********************/
            /*  Test de l'indice  */
            /**********************/
            sb.append("#Test si l'indice est négatif ou égal à 0\n");
            sb.append("bltz $v0, indiceNeg \n");

            sb.append("#Test si l'indice est supérieur à la taille du tableau\n");
            sb.append("#On charge la taille du tableau dans $t8\n");
            sb.append("lw $t8, " + (decalage - 4) + "($s7) \n");
            sb.append("#Différence de la taille du tableau et de l'indice\n");
            sb.append("sub $t8, $t8, $v0\n");
            sb.append("#Si la différence est inférieur ou égal à 0 on jump à l'erreur\n");
            sb.append("blez $t8, overTab\n");

            sb.append("# Multiplication par l'enjambée \n");
            sb.append("addi $t8, $zero, -4 \n");
            sb.append("mult $v0, $t8\n");
            sb.append("mflo $v0\n");

            sb.append("# Empile le décalage \n");
            sb.append("sw $v0, ($sp) \n");
            sb.append("addi $sp, $sp, -4 \n");

            sb.append("# Cherche l'adresse d'origine du tableau \n");
            sb.append("lw $v0, " + decalage + "($s7) \n");

            sb.append("# Calcul l'adresse de la case dans $v0\n");
            sb.append("addi $sp, $sp, 4\n");
            sb.append("lw $t8, 0($sp)\n");
            sb.append("add $a0, $v0, $t8\n");

            sb.append("# Charge la valeur de la case du tableau dans $v0\n");
            sb.append("lw $v0, ($a0) \n");
            return sb.toString();
        }

        @Override
        public String toString() {
            return getNom();
        }

}


