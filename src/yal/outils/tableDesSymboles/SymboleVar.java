package yal.outils.tableDesSymboles;

import yal.arbre.expression.Expression;

public class SymboleVar extends Symbole {

	private int pos;
	private Expression exp;
	private int noBloc;
	
	public SymboleVar() {
		pos = TableDesSymboles.getInstance().getCourant().getTailleZoneVariable();
		noBloc = TableDesSymboles.getInstance().getNbBloc();
	}
	
	public SymboleVar(Expression e) {
		pos = TableDesSymboles.getInstance().getCourant().getTailleZoneVariable();
		noBloc = TableDesSymboles.getInstance().getNbBloc();
		exp = e;
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
