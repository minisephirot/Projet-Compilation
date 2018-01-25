package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Egal extends Comparaison {

    public Egal(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " == ";
    }
    
	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		if (!this.droite.returnType.equals(this.gauche.returnType)){
			throw new AnalyseSemantiqueException("Ligne "+this.noLigne+" : Comparaison "+ this.operateur() +" doit être Bool avec Bool ou Int avec Int mais pas mixte");
		}
	}
	
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int indexEtiquette = 1;
		sb.append(super.toMIPS());
		
		sb.append("# Début comparaison\n");
		sb.append("Si"+ indexEtiquette +":\n");
		sb.append("# Soustraction des 2 variables comparées\n");
		sb.append("sub $v0, $t8, $v0\n");
		sb.append("# Comparaison à 0 du résultat\n");
		sb.append("beqz $v0, Sinon" + indexEtiquette +"\n");
		sb.append("# Si différent de 0 renvoie false\n");
		sb.append("Alors"+ indexEtiquette +":\n");
		sb.append("li $v0, 0\n");
		sb.append("b Fin"+ indexEtiquette +"\n");
		sb.append("# Sinon égal à 0 renvoie true\n");
		sb.append("Sinon"+ indexEtiquette +":\n");
		sb.append("li $v0, 1\n");
		sb.append("Fin"+ indexEtiquette +":\n");
				
		return sb.toString();
	}
}
