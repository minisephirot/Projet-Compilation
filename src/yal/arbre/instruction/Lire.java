package yal.arbre.instruction;

import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Lire extends Instruction {

	private String idf;
	private EntreeVar identificateur;
	
	public Lire(String i, int no) {
		super(no);
		idf = i;
		this.identificateur = new EntreeVar(idf);
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		if (!TableDesSymboles.getInstance().contains(identificateur)) {
			ListeErreursSemantiques.getInstance().addErreur("Ligne " + this.noLigne + " : Variable \"" + identificateur.getIdf() + "\" non déclarée");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int decalage = TableDesSymboles.getInstance().identifier(identificateur).getPos();
		sb.append("# Ecrire dans une variable \n");
		sb.append("li $v0 , 5\n");
		sb.append("syscall\n");
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($s7)\n");
		
		return sb.toString();
	}

}
