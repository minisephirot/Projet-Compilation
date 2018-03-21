package yal.outils.tableDesSymboles;

public class SymboleProg extends Symbole {

	int noBloc;

	public SymboleProg() {
		noBloc = TableDesSymboles.getInstance().getNbProchainBloc();
		System.out.println(noBloc);
	}
	
	public int getNoBloc() {
		return noBloc;
	}
}
