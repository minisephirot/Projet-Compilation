package yal.arbre.decoration;

import yal.arbre.ArbreAbstrait;
import yal.outils.tableDesSymboles.Entree;
import yal.outils.tableDesSymboles.TableDesSymboles;

/**
 * Initialise une varible déclarée à 0
 */
public class InitialisationVar extends ArbreAbstrait {

	Entree e;
	
	public InitialisationVar(int no, Entree e) {
		super(no);
		this.e = e;
	}

	@Override
	public void verifier() {
		//Rien a vérifier
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("add $v0, $zero, $zero\n");
		int decalage = TableDesSymboles.getInstance().identifier(e).getPos();
		sb.append("lw $v0, " + decalage + "($s7)\n");
		return sb.toString();
	}

}
