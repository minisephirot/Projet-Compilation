package yal.outils.tableDesSymboles;

import yal.outils.EtiquetteFactory;

public class SymboleProg extends Symbole {

	int noBloc;

	public SymboleProg() {
		//+1 au numéro de bloc car pas encore ouvert au moment de l'ajout du symbole
		noBloc = EtiquetteFactory.getInstance().getNumFonction();
	}
	
	public int getNoBloc() {
		return noBloc;
	}
}
