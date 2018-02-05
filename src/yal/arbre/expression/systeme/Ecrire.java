package yal.arbre.expression.systeme;

import yal.outils.EtiquetteFactory;

public class Ecrire extends System{

	protected Ecrire(int no, String s) {
		super(no,s);
	}

	@Override
	public void verifier() {
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		String printnumber = "printnumero"+EtiquetteFactory.getInstance().getIndexPrint();
		sb.append("# Print d'une string\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, "+ printnumber+"\n");
		sb.append("syscall\n");
		
		
		//.data de string insérés au mauvais endroit
		sb.insert(0, printnumber+":     .asciiz \"" +this.s+ "\\n\"\n");
		sb.insert(0, ".data\n");
		return sb.toString();
	}

}
