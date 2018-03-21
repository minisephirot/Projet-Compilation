package yal.arbre;

import yal.arbre.expression.idf.IDFFonc;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeProg;
import yal.outils.tableDesSymboles.SymboleProg;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Fonction extends ArbreAbstrait {

	private IDFFonc idf;
	private BlocDInstructions bloc;
	private int nbbloc;

	// Ajouter une classe paramètre
	public Fonction(int no, IDFFonc idf, BlocDInstructions bloc) {
		super(no);
		this.idf = idf;
		this.bloc = bloc;

		// On sauvegarde le numero de bloc
		this.nbbloc = TableDesSymboles.getInstance().getBlocActuel();
		
		int numParam = TableDesSymboles.getInstance().getNbParam();
		
		TableDesSymboles.getInstance().sortieBloc();
		TableDesSymboles.getInstance().ajouter(new EntreeProg(idf.getNom(), no, numParam), new SymboleProg()) ;
	}

	@Override
	public void verifier() {
		//Verifier que l'idf de la fonction n'existe déjà a été faite dans son ajout d'entreeprog au dictionnaire

		// Vérfifier la déclaration de la classe dans la TDS
		// Identifier la fonction dans la TDS
		// On entre dans le bon bloc
		TableDesSymboles.getInstance().entreeBloc(nbbloc);
		// Décorer l'arbre pour toutes les variables de la fonction
		bloc.verifier();
		TableDesSymboles.getInstance().sortieBloc();
	}

	@Override
	public boolean isRetourne(boolean bl) {
		// Vérifie qu'on a bien un retourne dans le bloc
		if(!bloc.isRetourne(true)) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.getNoLigne() + " : Fonction avec une branche sans instruction de retour.");
		}
		return true;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();

		sb.append("j finFonc"+nbbloc+"\n");
		sb.append("#Fonction " + idf + "\n");
		//Ajouter le label
		sb.append("fonc"+nbbloc+":\n");
		//Code vu en TD
		//Empiler l'addresse de retour
		sb.append("#Creation de la base de la pile \n");
		// Empile la valeur de retour
		sb.append("#Empile l'adresse de retour \n");
		sb.append("sw $ra,($sp) \n");
		sb.append("addi $sp,$sp,-4 \n");
		//Sauver la base locale de la pile (chainage dynamique)
		sb.append("#Sauver la base locale de la pile \n");
		sb.append("sw $s7,($sp) \n");
		sb.append("addi $sp,$sp,-4 \n");
		//Empiler le n° bloc
		sb.append("#Empiler le n° bloc \n");
		sb.append("li $v0,"+this.nbbloc+"\n");
		sb.append("sw $v0,($sp) \n");
		sb.append("addi $sp, $sp, -4 \n");
		//Init base locale
		sb.append("move $s7,$sp \n");
		//Reserver l'espace des var locales
		//sb.append("addi $sp,$sp,-4 \n");

		//Ajout du bloc
		sb.append("#Bloc d'instruction de la fonction "+idf+" \n");
		sb.append(bloc.toMIPS());

		//Etique quand retour detecter
		sb.append("sortieFonc"+nbbloc+":\n");
		
		//Restaurer le compteur ordinal
		sb.append("lw $ra, 12($s7) \n");
		//Restaurer le pointeur de pile
		sb.append("#Remonte la pile \n");
		sb.append("addi $sp, $s7, 12 \n");
		//Retrouver la base locale s7
		sb.append("lw $s7, 8($s7) \n"); 


		//Ajouter le jump du $ra
		sb.append("jr $ra \n");
		sb.append("finFonc"+nbbloc+":\n");
		
		return sb.toString();
	}
}
