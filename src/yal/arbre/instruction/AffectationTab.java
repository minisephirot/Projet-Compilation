package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;


public class AffectationTab extends Affectation {
    private Expression indice;

    public AffectationTab(IDFVar idf, Expression aff, Expression ind, int no) {
        super(idf, aff, no);
        indice = ind;
    }

    @Override
    public void verifier() {
        super.verifier();
        indice.verifier();
        if (!indice.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");

    }
}
