package yal.arbre;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFTab;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.EtiquetteFactory;
import yal.outils.tableDesSymboles.EntreeTab;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class DeclTab extends ArbreAbstrait {

    private Expression exp;
    private String idf;
    private int decalage;

    public DeclTab(Expression e, String idf, int no) {
        super(no);
        exp = e;
        this.idf = idf;
        
    }

    @Override
    public void verifier() {
        //Test si on est dans le bloc principal (donc expression constante)
        if(TableDesSymboles.getInstance().getNbBlocActuel() == 0)
            exp.verifierConstante();

        if (!exp.getReturnType().equals("int"))
            ListeErreursSemantiques.getInstance().addErreur(noLigne, "L'indice d'un tableau doit être un entier");
        SymboleVar s = (SymboleVar)(TableDesSymboles.getInstance().identifier(new EntreeTab(idf, noLigne)));
        decalage = s.getPos();
        EtiquetteFactory.getInstance().setHasTab();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        int indexEtiquette = EtiquetteFactory.getInstance().getIndexTant();
        EtiquetteFactory.getInstance().addIndexTant();
        sb.append("# Allocation du tableau " + idf + " \n");

        sb.append("# Enregistre l'adresse de début du tableau \n");
        sb.append("sw $sp, " + decalage + "($s7) \n");
        sb.append(exp.toMIPS());

        sb.append("#Test si l'indice est négatif ou égal à 0\n");
        sb.append("blez $v0, indiceNegNul \n");

        sb.append("# Enregistre la taille du tableau dans la pile \n");
        sb.append("sw $v0, " + (decalage-4) + "($s7) \n");

        sb.append("# Boucle Allocation du tableau \n");
        sb.append("Tantque" + indexEtiquette + " :\n");
        sb.append("beqz $v0, FinTantque" + indexEtiquette + "\n");
        sb.append("addi, $v0, $v0, -1\t# taille tab -1 \n");
        sb.append("lw $zero, ($sp)\t# mise à zéro de la case \n");
        sb.append("addi, $sp, $sp, -4\t# pile -4 \n");
        sb.append("b Tantque" + indexEtiquette + "\n");
        sb.append("FinTantque" + indexEtiquette + ":\n");

        return sb.toString();
    }
}
