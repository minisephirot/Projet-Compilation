package yal.arbre.instruction;

import yal.outils.EtiquetteFactory;

public class EcrireString extends Ecrire {

	private String str;
	private int printNumber;
	
	public EcrireString(String ch, int no) {
		super(no);
		if(ch.contains("\"\"")) { 
			ch = ch.replace("\"\"", "\\\"");
		}
		str = ch ;
	}

	@Override
	public void verifier() {
		// Enregistre la string a afficher dans EtiquetteFactory
		printNumber = EtiquetteFactory.getInstance().getIndexPrint();
		EtiquetteFactory.getInstance().addString("chaine" + printNumber + ": .asciiz " + this.str + "\n");
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Print d'une string\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine"+ printNumber + "\n");
		sb.append("syscall\n");
		return sb.toString();
	}

}
