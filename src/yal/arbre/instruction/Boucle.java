package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;

public class Boucle extends Instruction {

	private BlocDInstructions bi;
	private Expression expr;
	
	public Boucle(Expression e, int no) {
		super(no);
		expr = e;
	}

	public void ajouter(BlocDInstructions b) {
		bi = b;
	}
	
	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		return null;
	}

}
