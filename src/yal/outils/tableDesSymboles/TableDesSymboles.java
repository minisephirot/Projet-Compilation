package yal.outils.tableDesSymboles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import yal.arbre.BlocDInstructions;
import yal.arbre.decoration.AllocationVar;
import yal.arbre.decoration.InitialisationVar;
import yal.exceptions.ListeErreursSemantiques;

public class TableDesSymboles {

	/*
	 * Instance du singleton
	 */
	private static TableDesSymboles instance = new TableDesSymboles();

	/*
	 * Arraylist stoquant nos dictionnaires
	 */
	private ArrayList<Dictionnaire> TDS; 

	/*
	 *  Dictionnaire courant
	 */
	private Dictionnaire dcourant;

	/*
	 *  Dictionnaire principal (le "main")
	 */
	private Dictionnaire dprincipal;

	/*
	 * Initialise l'Arraylist
	 */
	private TableDesSymboles() {
		this.TDS = new ArrayList<Dictionnaire>();
		Dictionnaire d = new Dictionnaire();
		this.TDS.add(d);
		this.dprincipal = d;
	};

	/*
	 * Ajoute à l'Arraylist un dictionnaire dans le cas d'une entree dans un bloc 
	 * et le definies comme le dictionnaire courant
	 * true = parcours/verification des ID du dictionnaires
	 * false = on fais que créer des dictionnaires
	 */
	public void entreeBloc(boolean b ) {
		if (b){
			
		}else{
			Dictionnaire d = new Dictionnaire();
			this.TDS.add(d);
			this.dcourant = d;
		}
	}

	/*
	 * Sachant que on ne peux pas declarer de fonction dans une fonction, on aura toujours des programmes
	 * de profondeur 0 ou 1 la sortie d'un bloc amène toujours dans le programme principal (ou la fin du prog)
	 */
	public void sortieBloc(){
		this.dcourant = this.dprincipal;
	}

	/*
	 * Retourne le dictionnaire courant
	 */
	public Dictionnaire getCourant(){
		return this.dcourant;
	}

	public static TableDesSymboles getInstance() {
		return instance;
	}

}
