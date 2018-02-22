package yal.arbre.expression;

import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.Symbole;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDF extends Expression{

	private String nom;
	private int decalage;
	
	public IDF(String idf, int n) {
		super(n);
		this.returnType = "int"; //Tous nos variables sont des entiers
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		Symbole s = TableDesSymboles.getInstance().identifier(new EntreeVar(nom, noLigne));
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

}
