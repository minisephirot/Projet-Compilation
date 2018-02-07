package yal.outils.tableDesSymboles;

import yal.arbre.expression.Expression;

public class SymboleVar extends Symbole {
	private int pos;
	private Expression exp;
	
	public SymboleVar() {
		pos = TableDesSymboles.getInstance().getTailleZoneVariable();
	}
	
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public Expression getExp() {
		return exp;
	}

	public void setExp(Expression exp) {
		this.exp = exp;
	}

	public SymboleVar(Expression e) {
		pos = TableDesSymboles.getInstance().getTailleZoneVariable();
		exp = e;
	}
}
