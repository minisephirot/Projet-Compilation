package yal.outils;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Création du singleton qui gère la création d'étiquette
 */
public class EtiquetteFactory {

	private static EtiquetteFactory instance = new EtiquetteFactory();	//Instance du singleton
	int indexSi; 			// Nombre d'étiquette si alors sinon créé
	private int indexPrint;	// Nombre d'étiquette print créé
	private ArrayList<String> stringsPrint; // ArrayList des string a afficher
	private boolean hasDivBy0;	//Vérifie si des divisions sont utilisées
	private boolean hasBoolPrint;	//Vérifie si des ecrire de boolean sont utilisés
	
	private EtiquetteFactory() {
		indexSi = 0;
		indexPrint = 0;
		hasDivBy0 = false;
		hasBoolPrint = false;
		stringsPrint = new ArrayList<String>();
	};
	
	/**
	 * @return L'instance de l'EtiquetteFactory
	 */
	public static EtiquetteFactory getInstance() {
		return instance;
	}

	/**
	 * @return Le nombre d'étiquettes Si générées
	 */
	public int getIndexSi() {
		return indexSi;
	}
	
	
	/**
	 * Ajoute 1 au nombre d'étiquettes Si générées
	 */
	public void addIndexSi() {
		indexSi++;
	}
	
	/**
	 * @return Le nombre d'étiquettes chaine générées
	 */
	public int getIndexPrint() {
		return indexPrint;
	}
	
	/**
	 * Ajoute 1 au nombre d'étiquettes chaine générées
	 */
	public void addIndexPrint() {
		indexPrint++;
	}
	
	/**
	 * @return Un iterateur sur les string que l'utilisateur veux afficher
	 */
	public Iterator<String> getStrings() {
		return stringsPrint.iterator();
	}
	
	/**
	 * Ajoute une nouvelle string que l'uilisateur veut affichier
	 * @param s La string à afficher
	 */
	public void addString(String s){
		stringsPrint.add(s);
		addIndexPrint();
	}

	/**
	 * @return True si une division a été détectée
	 */
	public boolean hasDivBy0() {
		return hasDivBy0;
	}

	/*
	 * Met la variable à true si une division est utilisée mais ne peut pas
	 * être remis à false après coup
	 */
	public void setIndexDiv0() {
		this.hasDivBy0 = true;
	}
	
	/*
	 * Met la variable à true si un affichage de booleen est effectué et
	 * ajoute des chaines "vrai", "faux" à la liste de chaines
	 */
	public void setHasBoolPrint( ) {
		// Si c'est la première fois qu'on appelle cette fonction
		if (!hasBoolPrint) {
			this.hasBoolPrint = true;
			addString("chaineVrai: .asciiz \"vrai\"\n");
			addString("chaineFaux: .asciiz \"faux\"\n");
		}
	}
	

}
