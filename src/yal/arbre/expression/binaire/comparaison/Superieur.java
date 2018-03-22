package yal.arbre.expression.binaire.comparaison;

import yal.arbre.expression.Expression;
import yal.exceptions.ListeErreursSemantiques;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Superieur extends Comparaison {

    public Superieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " > ";
    }
    
	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		if (!(this.gauche.getReturnType().equals("int") && this.droite.getReturnType().equals("int"))){
			ListeErreursSemantiques.getInstance().addErreur(this.noLigne, "Opération arithmetique "+ this.operateur() +" doit être appliqué sur des entiers");
		}
	}
    
    
    @Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int indexEtiquette = getEtiquette();
		sb.append(super.toMIPS());
		
		sb.append("# Début comparaison supérieur\n");
		sb.append("Si"+ indexEtiquette +":\n");
		sb.append("# Soustraction des 2 variables comparées\n");
		sb.append("sub $v0, $t8, $v0\n");
		sb.append("# Comparaison à 0 du résultat\n");
		sb.append("bgtz $v0, Alors" + indexEtiquette +"\n");
		sb.append("# Sinon inférieur ou égal à 0 renvoie false\n");
		sb.append("Sinon"+ indexEtiquette +":\n");
		sb.append("li $v0, 0\n");
		sb.append("b Fin"+ indexEtiquette +"\n");
		sb.append("# Si supérieur à 0 renvoie true\n");
		sb.append("Alors"+ indexEtiquette +":\n");
		sb.append("li $v0, 1\n");
		sb.append("Fin"+ indexEtiquette +":\n");
				
		return sb.toString();
	}

}
