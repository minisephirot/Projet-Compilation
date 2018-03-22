package yal.outils.tableDesSymboles;

import yal.exceptions.ListeErreursSemantiques;

import java.util.HashMap;
import java.util.Map;

public class Dictionnaire {

	/*
	 * Hashmap stoquant nos entrées
	 */
	private HashMap<Entree,Symbole> TDS;
	private int num;
	
	/*
	 * Initialise la hashmap des entrées -> symboles
	 */
	public Dictionnaire(int num) {
		this.TDS = new HashMap<Entree,Symbole>();
		this.num = num;
	};

	
	/*
	 * Ajoute à la hashmap la paire (entrées -> symboles) et vérifie si il y a 
	 * une double declaration ou une incompatibilté type/expression
	 */
	public void ajouter(Entree e, Symbole s) {
		// Vérifie si on ne fait pas une double déclaration
		if (this.TDS.containsKey(e)) {
			String type = "Variable";
			if(e instanceof EntreeProg)
				type = "Fonction";
			ListeErreursSemantiques.getInstance().addErreur(e.getNoLigne(), type + " \"" + e.getIdf() + "\" déjà déclarée.");
		}
		this.TDS.put(e, s);
	}

	public int getNbParam() {
		int compteurParam = 0;
		for(Map.Entry<Entree,Symbole> entry : TDS.entrySet()) {			
			if(entry.getKey() instanceof EntreeParam)
				compteurParam++;				
		}
		return compteurParam;
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
	public int getTailleZoneVariable() {
		int cmpt = 0;
		for(Map.Entry<Entree,Symbole> e : TDS.entrySet()) {
			if (e.getKey() instanceof EntreeVar)
				cmpt++;
		}
		return cmpt * -4;
	}

	/**
	 * @return Le numéro du bloc qui correspond à ce dictionnaire
	 */
	public int getNum() {
		return num;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Entree,Symbole> entry : TDS.entrySet()) {
			sb.append(entry.getKey().getIdf() + "\n");
		}
		return sb.toString();
	}
}

