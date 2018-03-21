package yal.outils.tableDesSymboles;

import yal.exceptions.ListeErreursSemantiques;

import java.util.ArrayList;

public class TableDesSymboles {

	private static TableDesSymboles instance = new TableDesSymboles();	// Instance du singleton
	private ArrayList<Dictionnaire> TDS;	// Stockant nos dictionnaires
	private Dictionnaire dcourant;			// Dictionnaire courant
	private Dictionnaire dprincipal;		// Dictionnaire principal (le "main")

	/**
	 * Initialise l'Arraylist
	 */
	private TableDesSymboles() {
		this.TDS = new ArrayList<>();
		Dictionnaire d = new Dictionnaire(0);
		this.TDS.add(d);
		this.dprincipal = d;
		dcourant = d;
	}

	public static TableDesSymboles getInstance() {
		return instance;
	}

	public void ajouter(Entree e, Symbole s) {
		dcourant.ajouter(e, s);
	}

	/**
	 * Ajoute à l'Arraylist un dictionnaire dans le cas d'une entree dans un bloc 
	 * et le definies comme le dictionnaire courant
	 * true = parcours/verification des ID du dictionnaires
	 * false = on fais que créer des dictionnaires
	 */
	public void entreeBloc(int numBloc) {
		dcourant = TDS.get(numBloc);
	}

	/**
	 * Sachant que on ne peux pas declarer de fonction dans une fonction, on aura toujours des programmes
	 * de profondeur 0 ou 1 la sortie d'un bloc amène toujours dans le programme principal (ou la fin du prog)
	 */
	public void sortieBloc() {
		dcourant = dprincipal;
	}

	/**
	 * Retourne le dictionnaire courant
	 */
	public Dictionnaire getCourant(){
		return this.dcourant;
	}

	/**
	 * Recherche si un identifiant existe dans le dictionnaire courant (ou dans le chef)
	 * @param e L'entrée à vérifier
	 * @return Le symbole associé ou null si on a une erreur
	 */
	public Symbole identifier(Entree e){
		Symbole s =  dcourant.identifier(e);
		// Si null on test dans le chef
		if (s == null) {
			s = dprincipal.identifier(e);
			if (s == null) {
				ListeErreursSemantiques.getInstance().addErreur("Ligne " + e.getNoLigne() + " : Variable \"" + e.getIdf() + "\" non déclarée.");
			}
		}
		return s;
	}

	/**
	 *
	 * @return Le  decalage du nombre de variables du dictionnaire
	 */
	public int getTailleZoneVariable() {
		return dcourant.getTailleZoneVariable();
	}
	
	/**
	 * Création d'un nouveau bloc
	 */
	public void ajouterBloc() {
		Dictionnaire d = new Dictionnaire(TDS.size());
		this.TDS.add(d);
		this.dcourant = d;
	}
	
	/**
	 * @return Le nombre de param du bloc
	 */
	public int getNbParam() {
		return dcourant.getNbParam();
	}
	
	/**
	 * @return Le numéro de bloc dans le quel on se trouve
	 */
	public int getNbBlocActuel() {
		return dcourant.getNum();
	}

	/**
	 * @return Le numéro du prochain bloc qui sera crée
	 */
	public int getNbProchainBloc() {
		return TDS.size() - 1;
	}
}
