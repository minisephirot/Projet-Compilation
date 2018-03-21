package yal.arbre.decoration;

import yal.arbre.ArbreAbstrait;
import yal.outils.tableDesSymboles.TableDesSymboles;

/**
 * Déplace $sp pour l'allocation et met toutes les variables à 0
 */
public class AllocationVar extends ArbreAbstrait {

	int nombreVariables;	//Le nombre de variables à allouer

	public AllocationVar(int no) {
		super(no);
		nombreVariables = TableDesSymboles.getInstance().getTailleZoneVariable() / -4;
	}

	@Override
	public void verifier() {
		// Rien à vérifier
	}

	@Override
	public String toMIPS() {
		// Si on a plus de 0 variable à déclarer
		if (nombreVariables > 0) {
			int decalage = nombreVariables * -4;
			StringBuilder sb = new StringBuilder();
			// On initialise toutes les variables à zéro
			sb.append("# Init des variables à 0\n");
			sb.append("add $v0, $zero, $zero\n");
			for (int i = decalage; i < 0; i+=4) {
				sb.append("lw $v0, " + i + "($s7)\n");
			}
			sb.append("add $sp, $sp, " + decalage + "\n");
			return sb.toString();
		} else
			return "";
	}

}
