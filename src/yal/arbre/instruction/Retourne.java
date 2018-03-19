package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;

public class Retourne extends Instruction {

	private Expression exp;
	
	public Retourne(Expression e,int no) {
		super(no);
		exp = e;
	}

	@Override
	public void verifier() {
		if(exp.getReturnType() != "int") {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Type de retour doit être un entier");
		}
		// On vérifie l'expression
		exp.verifier();
	}

	@Override
	public boolean isRetourne() {
		return true;
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Retour de la fonction\n");
		// Calcul la valeur du retour
		sb.append(exp.toMIPS());
		sb.append("lw $v0, 12($s7) \n");
		return sb.toString();
	}

}
