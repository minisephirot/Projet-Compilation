package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Affectation extends Instruction{

	private EntreeVar identificateur;
	private Expression affection;
	
	public Affectation(String idf, Expression aff, int no) {
		super(no);
		this.identificateur = new EntreeVar(idf);
		this.affection = aff;
		
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		if (!TableDesSymboles.getInstance().contains(identificateur)) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Variable \"" + identificateur.getIdf() + "\" non déclarée");
		}
		// Vérifie si les types sont compatibles
		if (!identificateur.getType().equals(affection.getReturnType())) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Affectation de type \"" + affection.getReturnType() + "\" à la variable \"" + identificateur.getIdf() + "\" de type \"" + identificateur.getType() + "\"");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# calcul de la valeur a affecter à " + identificateur.getIdf() + "\n");
		sb.append(affection.toMIPS());
		
		int decalage = TableDesSymboles.getInstance().identifier(identificateur).getPos();
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($sp)\n");
		return sb.toString();
	}

}
