package yal.arbre.expression;

import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDF extends Expression{

	private EntreeVar identificateur;
	private String nom;
	private int decalage;
	
	public IDF(String idf, int n) {
		super(n);
		this.identificateur = new EntreeVar(idf);
		this.returnType = "int"; //Tous nos variables sont des entiers
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		if (!TableDesSymboles.getInstance().contains(identificateur)) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Variable \"" + identificateur.getIdf() + "\" non déclarée.");
		}
		// Changer en utilisant identifier de la TDS
		// Remplir déclage avec la TDS
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
		
		int decalage = TableDesSymboles.getInstance().identifier(identificateur).getPos();
		sb.append("# charge la variable dans $v0\n");
		sb.append("lw $v0, " + decalage + "($s7)\n");
		return sb.toString();
	}

}
