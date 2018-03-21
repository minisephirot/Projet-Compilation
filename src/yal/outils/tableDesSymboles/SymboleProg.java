package yal.outils.tableDesSymboles;

public class SymboleProg extends Symbole {

	int noBloc;

	public SymboleProg() {
		noBloc = TableDesSymboles.getInstance().getNbProchainBloc();
	}
	
	public int getNoBloc() {
		return noBloc;
	}
}
