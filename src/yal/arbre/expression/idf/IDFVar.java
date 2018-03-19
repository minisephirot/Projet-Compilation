package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.Symbole;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDFVar extends Expression{

	private String nom;
	private int decalage;
	
	public IDFVar(String idf, int n) {
		super(n);
		this.returnType = "int"; //Tous nos variables sont des entiers
		nom = idf;
	}

	@Override
	public void verifier() {
		//Verifier la présence de l'idf pour les fonctions ? -> IDF Classe abstraire avec IDFVar & IDFProg
		
		// Vérifie que la variable est déclarée
		SymboleVar s = (SymboleVar) TableDesSymboles.getInstance().identifier(new EntreeVar(nom, noLigne));
		if (s != null)
			decalage = s.getPos();
		else
			decalage = 1; // Décalage faux car la variable n'est pas décalrée
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getDecalage() {
		return decalage;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# charge la variable dans $v0\n");
		sb.append("lw $v0, " + decalage + "($s7)\n");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return getNom();
	}

}
