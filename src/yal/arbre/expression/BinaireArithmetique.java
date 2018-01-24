package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
        this.returntype = "int";
    }
    
	@Override
	public void verifier() {
		if (!(this.gauche.returntype.equals("int") && this.droite.returntype.equals("int"))){
			throw new AnalyseSemantiqueException("Ligne " + this.noLigne + " : Opération arithmetique "+ this.operateur() +" doit être appliqué sur des entiers");
		}
	}
    
}
