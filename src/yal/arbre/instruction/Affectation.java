package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Affectation extends Instruction{

	private EntreeVar identificateur;
	private Expression exp;
	
	public Affectation(String idf, Expression aff, int no) {
		super(no);
		this.identificateur = new EntreeVar(idf);
		this.exp = aff;
		
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		if (!TableDesSymboles.getInstance().contains(identificateur)) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Variable \"" + identificateur.getIdf() + "\" non déclarée");
		}
		// Vérifie si les types sont compatibles
		if (!identificateur.getType().equals(exp.getReturnType())) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Affectation de type \"" + exp.getReturnType() + "\" à la variable \"" + identificateur.getIdf() + "\" de type \"" + identificateur.getType() + "\"");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# calcul de la valeur a affecter à " + identificateur.getIdf() + "\n");
		sb.append(exp.toMIPS());
		
		int decalage = TableDesSymboles.getInstance().identifier(identificateur).getPos();
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($s7)\n");
		return sb.toString();
	}

}
