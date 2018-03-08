package yal.arbre;

import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Fonction extends ArbreAbstrait {
	
	private String idf;
	private BlocDInstructions bloc;

	// Ajouter une classe paramètre
	protected Fonction(int no, String idf, BlocDInstructions bloc) {
		super(no);
		this.idf = idf;
		this.bloc = bloc;
		
		// On entre dans un nouveau bloc
		TableDesSymboles.getInstance().entreeBloc(false);
		
	}

	@Override
	public void verifier() {
		// Vérifie qu'on a bien un retourne dans le bloc
		if(!bloc.verifierRetourne()) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.getNoLigne() + " : Fonction avec une branche sans instruction de retour.");
		} else {		
			// Vérfifier la déclaration de la classe dans la TDS
			// Identifier la fonction dans la TDS
			// On entre dans le bon bloc
			TableDesSymboles.getInstance().entreeBloc(true);

			// Décorer l'arbre pour toutes les variables de la fonction
			bloc.verifier();
			TableDesSymboles.getInstance().sortieBloc();
		}
		
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Fonction " + idf + "\n");
		//Ajouter le label
		sb.append(bloc.toMIPS());
		//Ajouter le jump du $ra
		return sb.toString();
	}
}
