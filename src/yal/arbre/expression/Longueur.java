package yal.arbre.expression;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFTab;
import yal.arbre.expression.idf.IDFVar;
import yal.outils.EtiquetteFactory;

public class Longueur extends Expression {

    private IDFTab idf;

    public Longueur(IDFTab idf, int no) {
        super(no);
        this.idf = idf;
        this.returnType = "int";
    }

    @Override
    public void verifier() {
        idf.verifier();
    }

    @Override
    public String toMIPS() {
        String itr = EtiquetteFactory.getInstance().getItr();
        StringBuilder sb = new StringBuilder();
        sb.append("# Trouve le tableau dans le bon bloc " + idf.getNom() + " \n");
        sb.append("move $t8, $s7 \n");
        sb.append(itr + ": \n");
        sb.append("lw $v0, 4($t8) \n");
        sb.append("addi $v1, $zero, " + idf.getNoBloc() + " \n");
        sb.append("sub $v0, $v0, $v1\n");
        sb.append("beqz $v0, fin" + itr + "\n");
        sb.append("lw $t8, 8($t8) \n");
        sb.append("j " + itr + " \n");
        sb.append("fin" + itr + ": \n");

        sb.append("#On charge la taille du tableau dans $v0\n");
        sb.append("lw $v0, " + (idf.getDecalage() - 4) + "($t8) \n");

        return sb.toString();
    }
}
