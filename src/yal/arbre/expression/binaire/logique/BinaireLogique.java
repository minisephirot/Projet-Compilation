package yal.arbre.expression.binaire.logique;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;
import yal.exceptions.ListeErreursSemantiques;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireLogique extends Binaire {

    protected BinaireLogique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
        this.setReturnType("bool");
    }
    
	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		if (!(this.gauche.getReturnType().equals("bool") && this.droite.getReturnType().equals("bool"))){
			ListeErreursSemantiques.getInstance().addErreur(this.noLigne, "Opération arithmetique "+ this.operateur() +" doit être appliqué sur des booléens");
		}
	}
}
