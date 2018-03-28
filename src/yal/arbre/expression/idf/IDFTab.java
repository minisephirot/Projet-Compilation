package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeTab;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDFTab extends Expression {

        private String nom;
        private Expression exp;
        private int decalage;
        private int noBloc;

        public IDFTab(String idf,Expression e, int n) {
            super(n);
            this.returnType = "int"; //Toutes nos variables sont des entiers
            nom = idf;
            exp = e;
        }

        @Override
        public void verifierConstante() {
            ListeErreursSemantiques.getInstance().addErreur(noLigne,"La déclaration d'un tableau dans le bloc principal doit se faire sans constante");
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
            exp.verifier();
            if (!exp.getReturnType().equals("int"))
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
            sb.append("# Evaluation du décalage dans le tableau \n");
            sb.append(exp.toMIPS());
            sb.append("# Empile le décalage \n");
            sb.append("sw $v0, ($sp) \n");
            sb.append("addi $sp, $sp, -4 \n");
            sb.append("# Stock l'adresse dans a0 \n");

            sb.append(" \n");

            return sb.toString();
        }

        @Override
        public String toString() {
            return getNom();
        }

}


