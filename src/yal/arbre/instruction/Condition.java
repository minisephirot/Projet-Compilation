package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;

public class Condition extends Instruction {
	
	private BlocDInstructions blocSi;
	private BlocDInstructions blocSinon;
	private Expression expr;

	public Condition(Expression e, int no) {
		super(no);
		expr = e;
	}
	
	public void ajouterSi(BlocDInstructions bi) {
		blocSi = bi;
	}
	
	public void ajouterSinon(BlocDInstructions bi) {
		blocSinon = bi;
	}
	
	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		return null;
	}

}
