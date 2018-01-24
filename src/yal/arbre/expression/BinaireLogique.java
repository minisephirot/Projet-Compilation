package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireLogique extends Binaire {

    protected BinaireLogique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
        this.returntype = "bool";
    }
    
	@Override
	public void verifier() {
		if (!(this.gauche.returntype.equals("bool") && this.droite.returntype.equals("bool"))){
			throw new AnalyseSemantiqueException("Ligne " + this.noLigne + " : Opération arithmetique "+ this.operateur() +" doit être appliqué sur des booléens");
		}
	}
}
