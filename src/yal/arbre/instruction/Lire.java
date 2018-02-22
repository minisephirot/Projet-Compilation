package yal.arbre.instruction;

import yal.arbre.expression.IDF;

public class Lire extends Instruction {

	private IDF idf;
	
	public Lire(IDF i, int no) {
		super(no);
		idf = i;
	}

	@Override
	public void verifier() {
		idf.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		//Décallage avec appel méthode getDécalage de idf
		int decalage = idf.getDecalage();
		sb.append("# Ecrire dans une variable \n");
		sb.append("li $v0, 5\n");
		sb.append("syscall\n");
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($s7)\n");
		
		return sb.toString();
	}

}
