package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Affectation extends Instruction{

	private String identificateur;
	private Expression affection;
	
	protected Affectation(String idf, Expression aff, int no) {
		super(no);
		this.identificateur = idf;
		this.affection = aff;
		
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		return null;
	}

}
