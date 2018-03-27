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
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");
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
}
