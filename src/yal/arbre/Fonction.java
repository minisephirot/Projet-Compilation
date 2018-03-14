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
		//Verifier que l'idf de la fonction n'existe déjà a été faite dans son ajout d'entreeprog au dictionnaire
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
		sb.append(this.idf+":\n");

		//Code vu en TD
		//Empiler l'addresse de retour
		sb.append("#Creation de la base de la pile \n");
		// Empile la valeur de retour
		sb.append("sw $ra,($sp) \n");
		sb.append("addi $sp,$sp,-4 \n");
		//Sauver la base locale de la pile (chainage dynamique)
		sb.append("sw $s7,($sp) \n"); 
		sb.append("addi $sp,$sp,-4 \n");
		//Empiler le n° bloc ????
		sb.append("li $v0,"+TableDesSymboles.getInstance().getNbBloc()+"\n");
		sb.append("sw $v0,($sp) \n");
		sb.append("addi $sp, $sp, -4 \n");
		//Init base locale
		sb.append("move $s7,$sp \n");
		//Reserver l'espace des var locales
		sb.append("addi $sp,$sp,-4 \n");

		//Ajout du bloc
		sb.append("#Bloc d'instruction de la fonction "+idf+" \n");
		sb.append(bloc.toMIPS());

		//Suite du TD
		//Restaurer le pointeur de pile
		sb.append("#Remonte la pile \n");
		sb.append("sw $sp,$s7, 12 \n");
		//Retrouver la base locale s7
		sb.append("lw $s7, 8($s7) \n"); //8 possiblement le nb d'entier déclarés, 2 dans l'exemple
		//Restaurer le compteur ordinal
		sb.append("lw $ra,($sp) \n");

		//Ajouter le jump du $ra
		sb.append("jr $ra \n");
		return sb.toString();
	}
}
