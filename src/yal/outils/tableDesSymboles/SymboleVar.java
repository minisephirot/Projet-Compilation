package yal.outils.tableDesSymboles;

import yal.arbre.expression.Expression;

public class SymboleVar extends Symbole {

	private int pos;
	private Expression exp;
	private int noBloc;
	
	public SymboleVar() {
		pos = TableDesSymboles.getInstance().getCourant().getTailleZoneVariable();
		noBloc = TableDesSymboles.getInstance().getNbBlocActuel();
	}
	
	public SymboleVar(Expression e) {
		pos = TableDesSymboles.getInstance().getCourant().getTailleZoneVariable();
		noBloc = TableDesSymboles.getInstance().getNbBlocActuel();
		exp = e;
	}
	
	public SymboleVar(boolean param) {
		pos = TableDesSymboles.getInstance().getNbParam();
		noBloc = TableDesSymboles.getInstance().getNbBlocActuel();
	}


	public int getPos() {
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
