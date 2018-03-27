package yal.arbre;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;

public class DeclTab extends ArbreAbstrait {

    private Expression exp;
    private IDFVar idf;

    public DeclTab(Expression e, IDFVar idf, int no) {
        super(no);
        exp = e;
        this.idf = idf;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
