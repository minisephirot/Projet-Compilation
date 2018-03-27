package yal.arbre.expression.unaire;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;

public class Longueur extends Expression {

    private IDFVar idf;

    public Longueur(IDFVar idf, int no) {
        super(no);
        this.idf = idf;
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        return null;
    }
}
