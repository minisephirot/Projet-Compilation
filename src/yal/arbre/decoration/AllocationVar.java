package yal.arbre.decoration;

import yal.arbre.ArbreAbstrait;
import yal.outils.tableDesSymboles.TableDesSymboles;

/**
 * Déplace $sp pour l'allocation et met toutes les variables à 0
 */
public class AllocationVar extends ArbreAbstrait {

	public AllocationVar(int no) {
		super(no);
	}

	@Override
	public void verifier() {
		// Rien à vérifier
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int decalage = TableDesSymboles.getInstance().getTailleZoneVariable();
		int cmpt = decalage / -4; //Nombre de variables
		if (cmpt > 0) {
			// On initialise toutes les variables à zéro
			sb.append("# Init des variables à 0\n");
			sb.append("add $v0, $zero, $zero\n");
			for (int i = decalage; i < 0; i+=4) {
				sb.append("lw $v0, " + i + "($s7)\n");
			}
		}
		sb.append("add $sp, $sp, " + decalage + "\n");
		return sb.toString();
	}

}
