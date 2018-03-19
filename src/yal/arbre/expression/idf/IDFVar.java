package yal.arbre.expression.idf;

import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.Symbole;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class IDFVar extends Expression{

	private String nom;
	private int decalage;
	private int noBloc;
	
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
		if (s != null) {
			decalage = s.getPos();
			noBloc = s.getNoBloc();
		} else {
			decalage = 1; // Décalage faux car la variable n'est pas décalrée
			noBloc = 0;
		}
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getDecalage() {
		return decalage;
	}

	public int getNoBloc() {
		return noBloc;
	}

	@Override
	public String toMIPS() {
		String itr = EtiquetteFactory.getInstance().getItr();
		StringBuilder sb = new StringBuilder();
		sb.append("# charge la variable " + nom + "\n");
		sb.append("move $t8, $s7 \n");
		sb.append(itr + ": \n");
		sb.append("lw $v0, 4($t8) \n");
		sb.append("addi $v1, $zero, " + noBloc + " \n");
		sb.append("sub $v0, $v0, $v1\n");
		sb.append("beqz $v0, fin" + itr + "\n");
		sb.append("lw $t8, 8($t8) \n");
		sb.append("j " + itr + " \n");
		sb.append("fin" + itr + ": \n");
		sb.append("lw $v0, " + decalage + "($t8)\n");
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return getNom();
	}

}
