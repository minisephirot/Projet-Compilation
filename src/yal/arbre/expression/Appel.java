package yal.arbre.expression;

import com.sun.java_cup.internal.runtime.Symbol;

import yal.arbre.expression.idf.IDFFonc;
import yal.outils.tableDesSymboles.EntreeProg;
import yal.outils.tableDesSymboles.SymboleProg;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Appel extends Expression {

	private IDFFonc idf;
	private int nbParam;
	
	public Appel(IDFFonc idfFonc,int n,int nbParam) {
		super(n);
		idf = idfFonc;
		returnType = "int";
		this.nbParam = nbParam;
	}

	@Override
	public void verifier() {
		//On verifie que la fonction appellée existe
		idf.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		//Reserver l'espace pour la valeur de retour
		sb.append("addi $sp, $sp, -4 \n");
		//Jump ou se trouve la fonction
		SymboleProg s = (SymboleProg) TableDesSymboles.getInstance().identifier(new EntreeProg(idf.getNom(), noLigne, nbParam));
		sb.append("jal fonc"+s.getNoBloc()+" \n");
		//Met le resultat dans $v0
		sb.append("addi $sp, $sp, 4 \n");
		sb.append("lw $v0, ($sp)\n");
		//Remonte la pile
		return sb.toString();
	}

}
