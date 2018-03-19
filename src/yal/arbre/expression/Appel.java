package yal.arbre.expression;

import yal.arbre.expression.idf.IDFFonc;

public class Appel extends Expression {

	private IDFFonc idf;
	
	public Appel(IDFFonc idfFonc,int n) {
		super(n);
		idf = idfFonc;
		returnType = "int";
	}

	@Override
	public void verifier() {
		//On verifie que la fonction appellée existe
		idf.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		//Reserver l'espace pour la valeur de retour
		sb.append("addi $sp, $sp, -4 \n");
		//Jump ou se trouve la fonction
		sb.append("jal "+this.idf.toString()+" \n");
		//Met le resultat dans $v0
		sb.append("lw $v0, ($sp)\n");
		//Remonte la pile
		sb.append("addi $sp, $sp, 4 \n");
		return sb.toString();
	}

}
