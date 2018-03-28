package yal.arbre;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeTab;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class DeclTab extends ArbreAbstrait {

    private Expression exp;
    private IDFVar idf;
    private int decalage;

    public DeclTab(Expression e, IDFVar idf, int no) {
        super(no);
        exp = e;
        this.idf = idf;
    }

    @Override
    public void verifier() {
        exp.verifier();

        //Test si on est dans le bloc principal (donc expression constante)
        if(TableDesSymboles.getInstance().getNbBlocActuel() == 0)
            exp.verifierConstante();

        if (!exp.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");

        SymboleVar s = (SymboleVar) TableDesSymboles.getInstance().identifier(new EntreeTab(idf.getNom(), noLigne));
        decalage = s.getPos();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append("# Allocation du tableau " + idf + " \n");
        sb.append("# Enregistre l'adresse de début du tableau \n");
        sb.append("sw $sp, " + decalage + "($s7) \n");
        sb.append(exp.toMIPS());
        sb.append("# Enregistre la taille du tableau dans la pile \n");
        sb.append("sw $v0, " + (decalage-4) + "($s7) \n");
        sb.append("# Calcul le décalage du tabeau \n");
        sb.append("addi, $t8, $zero, -4 \n");
        sb.append("mult $v0, $t8\n");
        sb.append("mflo $v0\n");
        sb.append("# Allocation du tableau \n");
        sb.append("addi, $sp, $sp, $v0 \n");

        return sb.toString();
    }
}
