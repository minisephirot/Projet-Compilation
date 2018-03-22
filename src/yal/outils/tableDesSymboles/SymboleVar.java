package yal.outils.tableDesSymboles;

import yal.arbre.expression.Expression;

public class SymboleVar extends Symbole {

	private int pos;
	private Expression exp;
	private int noBloc;
	private boolean isParam;	// Si la variable correspond à une paramètre

	/**
	 * Symbole de variable
	 * @param param si true c'est un paramètre de fonction, sinon c'est une variable simple
	 */
	public SymboleVar(boolean param) {
		noBloc = TableDesSymboles.getInstance().getNbBlocActuel();
		if (param) {
			pos = TableDesSymboles.getInstance().getNbParam();
		} else {
			pos = TableDesSymboles.getInstance().getCourant().getTailleZoneVariable();
		}
		isParam = param;
	}

	public int getPos() {
		// Si c'est un paramètre, il faut remonter la pile + retrouver le on emplacement
		if (isParam) {
			return (TableDesSymboles.getInstance().getNbParam() - pos) * 4 + 16;
		}
		return pos;
	}

	public int getNoBloc() {
		return noBloc;
	}

	public String getType() {
		if (exp == null)
			return "int";
		return exp.getReturnType();
	}
}
