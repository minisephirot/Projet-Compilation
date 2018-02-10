package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class EcrireVar extends Ecrire {

	private Expression exp;
	
	public EcrireVar(Expression e, int no) {
		super(no);
		exp = e;
	}

	@Override
	public void verifier() {
		// Vérifie l'expression
		exp.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("move $v1, $v0\n");
		sb.append("# Print d'une expression\n");
		sb.append(exp.toMIPS());
		sb.append("add $a0, $v0, $zero\n");
		sb.append("li $v0, 1\n");
		sb.append("syscall\n");
		sb.append("move $v0, $v1\n");
		return sb.toString();
	}

}
