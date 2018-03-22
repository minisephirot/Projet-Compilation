package yal.arbre.expression;

import java.util.ArrayList;

import yal.arbre.expression.idf.IDFFonc;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.tableDesSymboles.EntreeProg;
import yal.outils.tableDesSymboles.EntreeVar;
import yal.outils.tableDesSymboles.SymboleProg;
import yal.outils.tableDesSymboles.SymboleVar;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Appel extends Expression {

	private IDFFonc idf;
	private int nbParam;
	private SymboleProg s;
	private ArrayList<Expression> listeexpr;
	
	public Appel(IDFFonc idfFonc,int n,ArrayList<Expression> expressions) {
		super(n);
		idf = idfFonc;
		returnType = "int";
		this.listeexpr = expressions;
		this.nbParam = this.listeexpr.size();
	}

	@Override
	public void verifier() {
		//On verifie que la fonction appellée existe
		idf.verifier();

		for (Expression exp : listeexpr) {
			exp.verifier();
			// Les paramètres doivent être des entiers
			if (exp.getReturnType() != "int")
				ListeErreursSemantiques.getInstance().addErreur(noLigne, "Les paramètres de la fonction \"" + idf.getNom() + "\" doivent être des entiers");
		}

	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		//Gestion des parametres -> On empile les différentes valeures des parametres dans la base locale
		for (Expression e : this.listeexpr){
			if (e instanceof IDFVar){//Si c'est une variable
				IDFVar variable = (IDFVar) e;
				sb.append("# passage de  " + variable.getNom() + " en parametre \n");
				SymboleVar symbvar = (SymboleVar) TableDesSymboles.getInstance().identifier(new EntreeVar(variable.getNom(),0));
				int decalage = symbvar.getPos();
				sb.append("# sw au bon endroit\n");
				sb.append("sw $sp, " + decalage + "($sp)\n");
			}else//C'est une constante (sinon ça à crash)
			{
				sb.append("# sw de la constante passée en parametre\n");
				sb.append(e.toMIPS());
				sb.append("sw $v0,($sp)\n");
			}
			//On décale sp dans tout les cas
			sb.append("add $sp, $sp, " + -4 + "\n");
		}
		
		//Reserver l'espace pour la valeur de retour
		sb.append("addi $sp, $sp, -4 \n");
		//Jump ou se trouve la fonction
		sb.append("jal fonc"+s.getNoBloc()+" \n");
		//Met le resultat dans $v0
		sb.append("addi $sp, $sp, 4 \n");
		sb.append("lw $v0, ($sp)\n");
		//Remonte la pile
		return sb.toString();
	}

}
