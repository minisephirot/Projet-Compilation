package yal.arbre;

public class Fonction extends ArbreAbstrait {
	
	private String idf;
	private BlocDInstructions bloc;

	// Ajouter une classe paramètre
	protected Fonction(int no, String idf, BlocDInstructions bloc) {
		super(no);
		this.idf = idf;
		this.bloc = bloc;
	}

	@Override
	public void verifier() {
		// Vérfifier la déclaration dans la TDS
		// Identifier la fonction dans la TDS
		// Décorer l'arbre pour toutes les variables de la fonction
		bloc.verifier();
		
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
