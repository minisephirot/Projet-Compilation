package yal.outils.tableDesSymboles;

public class SymboleProg extends Symbole {

	int noBloc;

	public SymboleProg() {
		noBloc = TableDesSymboles.getInstance().getBlocActuel() + 1;
	}
	
	public int getNoBloc() {
		return noBloc;
	}
}
