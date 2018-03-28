package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeProg;
import yal.outils.tableDesSymboles.SymboleProg;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDFFonc extends Expression{

	private String nom;
	private int numParam;
	private SymboleProg s;

	public IDFFonc(String idf, int n, int numParam) {
		super(n);
		this.returnType = "int"; //Toutes nos fonctions sont des entiers
		nom = idf;
		this.numParam = numParam;
	}

	@Override
	public void verifierConstante() {
		ListeErreursSemantiques.getInstance().addErreur(noLigne,"La déclaration d'un tableau dans le bloc principal doit se faire sans variable");
	}

	@Override
	public void verifier() {
		// Vérifie que la fonction est déclarée ET que il respecte le bon nb de parametres
		s = (SymboleProg) TableDesSymboles.getInstance().identifier(new EntreeProg(nom, noLigne,numParam));
	}
	
	public String getNom() {
		return nom;
	}

	@Override
	public String toMIPS() {
		return "";
	}
	
	@Override
	public String toString() {
		return getNom();
	}

	public SymboleProg getSymbole() {
		return s;
	}
}