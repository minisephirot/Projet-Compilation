package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;

public class EcrireVar extends Ecrire {

	private Expression exp;
	private boolean isBool;
	
	public EcrireVar(Expression e, int no) {
		super(no);
		exp = e;
		isBool = false;
	}

	@Override
	public void verifier() {
		// Vérifie l'expression
		exp.verifier();
		if (exp.getReturnType().equals("bool")) {
			isBool = true;
			EtiquetteFactory.getInstance().setHasBoolPrint();
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("move $v1, $v0\n");
		sb.append("# Print d'une expression\n");
		sb.append(exp.toMIPS());
		
		//Si on affiche un booleen
		if (isBool) {
			// Demande une nouvelle etiquette
			int indexEtiquette = EtiquetteFactory.getInstance().getIndexSi();
			EtiquetteFactory.getInstance().addIndexSi();
			sb.append("# Test valeur bool a afficher\n");
			sb.append("beqz $v0, AfficheFaux" + indexEtiquette +"\n");
			sb.append("# Affiche chaine vrai\n");
			sb.append("la $a0, chaineVrai\n");
			sb.append("b AfficheBoolFin"+ indexEtiquette +"\n");
			sb.append("AfficheFaux" + indexEtiquette + ":\n");
			sb.append("# Affiche chaine faux\n");
			sb.append("la $a0, chaineFaux\n");
			sb.append("AfficheBoolFin" + indexEtiquette + ":\n");
			sb.append("li $v0, 4\n");
		} else {
			sb.append("add $a0, $v0, $zero\n");
			sb.append("li $v0, 1\n");
		}
		
		sb.append("syscall\n");
		sb.append("move $v0, $v1\n");
		return sb.toString();
	}

}
