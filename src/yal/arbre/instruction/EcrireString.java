package yal.arbre.instruction;

import yal.outils.EtiquetteFactory;

public class EcrireString extends Ecrire {

	private String str;
	
	public EcrireString(String ch, int no) {
		super(no);
		str = ch;
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
		System.out.println("Je m'occupe de tomips une string");
		EtiquetteFactory.getInstance().addString(printnumber+": .asciiz "+this.str+"\n");
		return sb.toString();
	}

}
