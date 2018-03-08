package yal.outils.tableDesSymboles;

import java.util.HashMap;
import java.util.Iterator;

import yal.arbre.BlocDInstructions;
import yal.arbre.decoration.AllocationVar;
import yal.arbre.decoration.InitialisationVar;
import yal.exceptions.ListeErreursSemantiques;

public class Dictionnaire {

	/*
	 * Hashmap stoquant nos entrées
	 */
	private HashMap<Entree,Symbole> TDS; 

	/*
	 * Initialise la hashmap des entrées -> symboles
	 */
	public Dictionnaire() {
		this.TDS = new HashMap<Entree,Symbole>();
	};

	/*
	 * Ajoute à la hashmap la paire (entrées -> symboles) et vérifie si il y a 
	 * une double declaration ou une incompatibilté type/expression
	 */
	public void ajouter(Entree e, Symbole s, int noligne) {
		// Vérifie si on ne fait pas une double déclaration
		if (this.TDS.containsKey(e)) {
			String type = "Variable";
			if(e instanceof EntreeProg)
				type = "Fonction";
			ListeErreursSemantiques.getInstance().addErreur("Ligne "+noligne+" : " + type + " \"" + e.getIdf() + "\" déjà déclarée.");
		}
		this.TDS.put(e, s);
	}

	/*
	 * Booleen pour throw des erreurs de double déclaration ou de non déclaration
	 */
	public boolean contains(Entree e){
		return this.TDS.containsKey(e);
	}

	/*
	 * Getter du symbole associé à la clef
	 */
	public Symbole identifier(Entree e){
		if (!contains(e)) {
			return null;
		}
		return this.TDS.get(e);
	}

	/*
	 *  return l'empilement max de la pile de variables
	 */
	public int getTailleZoneVariable(){
		return TDS.size() * -4;
	}

	/**
	 * Ajoute le décalage et les initialisations des variables
	 * @param bi Le bloc a decorer
	 */
	public void decorerArbre(BlocDInstructions bi) {
		// Initialise toutes les variables à 0
		Iterator<Entree> it = TDS.keySet().iterator();
		while (it.hasNext()) {
			Entree tmp = it.next();
			bi.ajouterDebut(new InitialisationVar(0, tmp));
		}
		// Ajoute le déclage dans $sp
		bi.ajouterDebut(new AllocationVar(0, getTailleZoneVariable()));
	}

}

