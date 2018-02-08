package yal.outils.tableDesSymboles;

import java.util.HashMap;

import yal.exceptions.ListeErreursSemantiques;

public class TableDesSymboles {

	/*
	 * Instance du singleton
	 */
	private static TableDesSymboles instance = new TableDesSymboles();
	
	/*
	 * Hashmap stoquant nos entrées
	 */
	private HashMap<Entree,Symbole> TDS; 
	
	/*
	 * Initialise la hashmap des entrées -> symboles
	 */
	private TableDesSymboles() {
		this.TDS = new HashMap<Entree,Symbole>();
	};
	
	/*
	 * Ajoute à la hashmap la paire (entrées -> symboles) et vérifie si il y a 
	 * une double declaration ou une incompatibilté type/expression
	 */
	public void ajouter(Entree e, Symbole s) {
		// Vérifie si on ne fait pas une double déclaration
		if (this.TDS.containsKey(e))
			ListeErreursSemantiques.getInstance().addErreur("Variable \"" + e.getIdf() + "\" déjà déclarée");
		this.TDS.put(e, s);
	}
	
	/*
	 * Booleen pour throw des erreurs de double déclaration
	 */
	public boolean contains(Entree e){
		return this.TDS.containsKey(e);
	}
	
	/*
	 * Getter du symbole associé à la clef
	 */
	public Symbole identifier(Entree e){
		return this.TDS.get(e);
	}
	
	/*
	 * 				???
	 */
	public int getTailleZoneVariable(){
		return TDS.size() * -4;
	}
	
	public static TableDesSymboles getInstance() {
		return instance;
	}
	
}
