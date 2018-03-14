package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.Symbole;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDFFonc extends Expression{

	private String nom;
	
	public IDFFonc(String idf, int n) {
		super(n);
		this.returnType = "int"; //Tous nos variables sont des entiers
		nom = idf;
	}

	@Override
	public void verifier() {

	}
	
	public String getNom() {
		return nom;
	}

	@Override
	public String toMIPS() {
		return null;
	}
	
	@Override
	public String toString() {
		return getNom();
	}

}