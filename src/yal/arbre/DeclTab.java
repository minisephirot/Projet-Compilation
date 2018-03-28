package yal.arbre;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.TableDesSymboles;

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
        exp.verifier();
        if(TableDesSymboles.getInstance().getNbBlocActuel() == 0)
            exp.verifierConstante();
        if (!exp.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");
        //Test si on est dans le bloc principal (donc expression constante)

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
