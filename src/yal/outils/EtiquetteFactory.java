package yal.outils;

import java.util.ArrayList;

/*
 * Création du singleton qui gère la création d'étiquette
 */
public class EtiquetteFactory {

	private static EtiquetteFactory instance = new EtiquetteFactory();	//Instance du singleton
	private int indexSi; 			// Nombre d'étiquette si alors sinon créé
	private int indexTant; 			// Nombre d'étiquette tantque créé
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
	 * @return Le nombre d'étiquettes Tantque générées
	 */
	public int getIndexTant() {
		return indexTant;
	}
	
	/**
	 * Ajoute 1 au nombre d'étiquettes Tantque générées
	 */
	public void addIndexTant() {
		indexTant++;
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
	 * Ajoute une nouvelle string que l'uilisateur veut affichier
	 * @param s La string à afficher
	 */
	public void addString(String s){
		stringsPrint.add(s);
		addIndexPrint();
	}

	/**
	 * Met la variable à true si une division est utilisée mais ne peut pas
	 * être remis à false après coup
	 */
	public void setIndexDiv0() {
		if (!hasDivBy0) {
			this.hasDivBy0 = true;
			addString("errDiv0: .asciiz \" ERREUR EXECUTION : Division par 0 interdite\\n\"\n");
		}
	}
	
	/**
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
	
	/**
	 * Ecrit les chaines de caractères à afficher
	 * @return les chaines a écrire
	 */
	public String ecrireChaines() {
		if (stringsPrint.isEmpty())
			return "";
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(".data\n");
			for (String string : stringsPrint) {
				sb.append(string);
			}
			return sb.toString();
		}
	}
	
	/**
	 * Ecrit le test de div par 0 si besoin
	 * @return Le test ou rien si inutile
	 */
	public String ecrireTestDiv0() {
		if (hasDivBy0) {
			StringBuilder sb = new StringBuilder();
			sb.append("# La gestion d'une division par 0\n");
			sb.append("divByZero:\n");
			sb.append("li $v0, 4\n");
			sb.append("la $a0, errDiv0\n");
			sb.append("syscall\n");
			sb.append("b end\n");
			return sb.toString();
		} else
			return "";
	}
}
