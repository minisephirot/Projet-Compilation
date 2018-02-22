package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.arbre.expression.IDF;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Affectation extends Instruction{

	private Expression exp;
	private IDF idf;
	
	public Affectation(IDF idf, Expression aff, int no) {
		super(no);
		this.exp = aff;
		this.idf = idf;
		
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		idf.verifier();
		
		// Vérifie si les types sont compatibles
		if (!idf.getReturnType().equals(exp.getReturnType())) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Affectation de type \"" + exp.getReturnType() + "\" à la variable \"" + idf.getNom() + "\" de type \"" + idf.getReturnType() + "\"");
		}
		exp.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# calcul de la valeur a affecter à " + idf.getNom() + "\n");
		sb.append(exp.toMIPS());
		
		int decalage = idf.getDecalage();
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($s7)\n");
		return sb.toString();
	}

}
