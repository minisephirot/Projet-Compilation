package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFTab;
import yal.exceptions.ListeErreursSemantiques;


public class AffectationTab extends Instruction {

    private Expression affecation;
    private IDFTab idf;

    public AffectationTab(IDFTab idf, Expression aff, int no) {
        super(no);
        affecation = aff;
        this.idf = idf;
    }

    @Override
    public void verifier() {

        // Vérifie l'idenifiant du tableau
        idf.verifier();

        // Vérifie l'expression à affecter
        affecation.verifier();
        if (!affecation.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'expression à affecter au tableau doit être un entier");

    }

    @Override
    public String toMIPS() {
        // Ici l'adresse est déjà empilée
        StringBuilder sb = new StringBuilder();

        sb.append("# Calcul de l'expression \n");
        sb.append(affecation.toMIPS());

        sb.append("# Dépile l'adresse de la case \n");
        sb.append("addi $sp, $sp, 4\n");
        sb.append("lw $a0, 0($sp)\n");

        sb.append("# Met la valeur dans le tableau \n");
        sb.append("sw $v0, ($a0)");

        return sb.toString();
    }
}
