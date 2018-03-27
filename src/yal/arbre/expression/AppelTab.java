package yal.arbre.expression;

import yal.arbre.expression.constante.Constante;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class AppelTab extends Expression {

    private Expression exp;
    private IDFVar idf;

    public AppelTab(Expression e, IDFVar idf, int no) {
        super(no);
        exp = e;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        idf.verifier();
        exp.verifier();
        if (!exp.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "La taille d'un tableau doit être un entier");

        //Test si on est dans le bloc principal (donc expression constante)

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
