package yal.outils.tableDesSymboles;

public class SymboleProg extends Symbole {

	int noBloc;

	public SymboleProg() {

		noBloc = TableDesSymboles.getInstance().getBlocSuivant();
	}
	
	public int getNoBloc() {
		return noBloc;
	}
}
