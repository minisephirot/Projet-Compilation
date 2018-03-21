package yal.outils.tableDesSymboles;

import java.util.ArrayDeque;
import java.util.ArrayList;

import yal.exceptions.ListeErreursSemantiques;

public class TableDesSymboles {

	/**
	 * Instance du singleton
	 */
	private static TableDesSymboles instance = new TableDesSymboles();

	/**
	 * Arraylist stoquant nos dictionnaires
	 */
	private ArrayList<Dictionnaire> TDS; 
	
	/**
	 * ArrayList stoquant les bloc que l'on a ouvert et parcouru lors de l'entrée et la sortie d'un bloc
	 */
	private ArrayDeque<Integer> listeBloc;

	/**
	 *  Dictionnaire courant
	 */
	private Dictionnaire dcourant;
	private int compteurBloc; // Permet de positionner le bon dictionnaire en tant que courant lors de la vérif
	
	/**
	 *  Dictionnaire principal (le "main")
	 */
	private Dictionnaire dprincipal;

	/**
	 * Initialise l'Arraylist
	 */
	private TableDesSymboles() {
		this.TDS = new ArrayList<>();
		Dictionnaire d = new Dictionnaire();
		listeBloc = new ArrayDeque<Integer>();
		listeBloc.add(0);
		this.TDS.add(d);
		this.dprincipal = d;
		dcourant = d;
		compteurBloc = 0;
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
			listeBloc.addLast(numBloc);
	}

	/**
	 * Sachant que on ne peux pas declarer de fonction dans une fonction, on aura toujours des programmes
	 * de profondeur 0 ou 1 la sortie d'un bloc amène toujours dans le programme principal (ou la fin du prog)
	 */
	public void sortieBloc() {
		listeBloc.removeLast();
		this.dcourant = TDS.get(listeBloc.getLast());		
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
	 *
	 * @return Le  decalage du nombre de variables du dictionnaire
	 */
	public int getTailleZoneVariable(int index) {
		return this.TDS.get(index).getTailleZoneVariable();
	}
	
	/**
	 *
	 * @return Le numéro de bloc du dictionnaire courant
	 */
	public int getNbBloc() {
		return this.compteurBloc;
	}
	
	/**
	 * Création d'un nouveau bloc
	 */
	public void ajouterBloc() {
		Dictionnaire d = new Dictionnaire();
		this.TDS.add(d);
		this.dcourant = d;
		listeBloc.addLast(compteurBloc);
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
	public int getBlocActuel() {
		
		return listeBloc.getLast();
	}
	
	public int getBlocSuivant() {
		compteurBloc++;
		return compteurBloc;
	}

}
