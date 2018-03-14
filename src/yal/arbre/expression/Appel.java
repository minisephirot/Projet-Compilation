package yal.arbre.expression;

public class Appel extends Expression {

	private IDFVar idf;
	
	public Appel(IDFVar i,int n) {
		super(n);
		idf = i;
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
		return sb.toString();
	}

}
