package yal.outils.tableDesSymboles;

import yal.arbre.BlocDInstructions;
import yal.exceptions.ListeErreursSemantiques;

public class EntreeProg extends Entree {
	
	BlocDInstructions bloc;
	
	public EntreeProg(String idf, int noLigne) {
		super(idf, noLigne);
	}

	public EntreeProg(String idf, BlocDInstructions bl, int noLigne) {
		super(idf, noLigne);
		bloc = bl;
	}
	
	public void verifierRetourne() {
		if(!bloc.verifierRetourne()) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.getNoLigne() + " : Fonction avec une branche sans instruction de retour.");
		}
	}
	
	public void verifier() {
		bloc.verifier();
		verifierRetourne();
	}

}
