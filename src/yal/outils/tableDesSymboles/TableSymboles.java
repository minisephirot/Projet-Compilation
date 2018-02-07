package yal.outils.tableDesSymboles;

import java.util.HashMap;

public class TableSymboles {

	/*
	 * Instance du singleton
	 */
	private static TableSymboles instance = new TableSymboles();
	
	/*
	 * Hashmap stoquant nos entrées
	 */
	private HashMap<Entree,Symbole> TDS; 
	
	/*
	 * Initialise la hashmap des entrées -> symboles
	 */
	private TableSymboles() {
		this.TDS = new HashMap<Entree,Symbole>();
	};
	
	/*
	 * Ajoute à la hashmap la paire (entrées -> symboles) et vérifie si il y a 
	 * une double declaration ou une incompatibilté type/expression
	 */
	public void ajouter(Entree e, Symbole s){
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
	public Symbole get(Entree e){
		return this.TDS.get(e);
	}
	
	/*
	 * 				???
	 */
	public int getTailleZoneVariable(){
		return TDS.size() * -4;
	}
	
	public static TableSymboles getInstance() {
		return instance;
	}
	
	
}
