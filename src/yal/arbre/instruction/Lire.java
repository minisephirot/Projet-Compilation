package yal.arbre.instruction;

import yal.arbre.expression.IDF;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Lire extends Instruction {

	private IDF idf;
	private EntreeVar identificateur;
	
	public Lire(IDF i, int no) {
		super(no);
		idf = i;
	}

	@Override
	public void verifier() {
		
		idf.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		//Décallage avec appel méthode getDécalage de idf
		int decalage = TableDesSymboles.getInstance().identifier(identificateur).getPos();
		sb.append("# Ecrire dans une variable \n");
		sb.append("li $v0 , 5\n");
		sb.append("syscall\n");
		sb.append("# sw au bon endroit\n");
		sb.append("sw $v0, " + decalage + "($s7)\n");
		
		return sb.toString();
	}

}
