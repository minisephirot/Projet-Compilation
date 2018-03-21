package yal.outils.tableDesSymboles;

public class SymboleParam extends Symbole {
	private int numVar;
	
	public SymboleParam() {
		super();
		numVar = TableDesSymboles.getInstance().getNbParam();
	}
}
