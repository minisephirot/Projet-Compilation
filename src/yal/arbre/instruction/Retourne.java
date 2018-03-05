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
	}

	@Override
	public boolean isRetourne() {
		return true;
	}
	
	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

}
