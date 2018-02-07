package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Ecrire extends Instruction {

	private Expression exp;
	private String str;
	
	public Ecrire(Expression e, int no) {
		super(no);
		exp = e;
	}
	
	public Ecrire(String ch, int no) {
		super(no);
		str = ch;
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		return null;
	}

}
