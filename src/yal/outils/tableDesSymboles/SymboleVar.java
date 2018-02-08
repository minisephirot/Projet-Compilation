package yal.outils.tableDesSymboles;

import yal.arbre.expression.Expression;

public class SymboleVar extends Symbole {
	private int pos;
	private Expression exp;
	
	public SymboleVar() {
		pos = TableDesSymboles.getInstance().getTailleZoneVariable();
	}
	
	public SymboleVar(Expression e) {
		pos = TableDesSymboles.getInstance().getTailleZoneVariable();
		exp = e;
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public String getType() {
		if (exp == null)
			return "int";
		return exp.getReturnType();
	}
}
