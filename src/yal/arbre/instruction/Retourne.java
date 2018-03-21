package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;

public class Retourne extends Instruction {

	private Expression exp;
	private int numBloc;

	public Retourne(Expression e,int no,int numBloc) {
		super(no);
		exp = e;
		this.numBloc = numBloc;
	}

	@Override
	public void verifier() {
		if(exp.getReturnType() != "int") {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Type de retour doit être un entier");
		}
		// On vérifie l'expression
		exp.verifier();
	}

	@Override
	public boolean isRetourne(boolean isFonction) {
		if(!isFonction)
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.getNoLigne() + " : Instruction de retour dans le bloc principal.");
		return true;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Retour de la fonction\n");
		// Calcul la valeur du retour
		sb.append(exp.toMIPS());
		sb.append("sw $v0, 16($s7) \n");
		sb.append("j sortieFonc"+numBloc+" \n");

		return sb.toString();
	}

}
