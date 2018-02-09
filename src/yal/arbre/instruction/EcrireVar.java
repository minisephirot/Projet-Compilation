package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;

public class EcrireVar extends Ecrire {

	private Expression exp;
	
	public EcrireVar(Expression e, int no) {
		super(no);
		exp = e;
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		String printnumber = "printnumero"+EtiquetteFactory.getInstance().getIndexPrint();
		sb.append("# Print d'une string\n");
		sb.append("move $v1, $v0\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, "+ printnumber+"\n");
		sb.append("syscall\n");
		sb.append("move $v0, $v1\n");
		
		//.data de string insérés au mauvais endroit

		return sb.toString();
	}

}
