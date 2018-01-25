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
        this.returnType = "int";
    }
    
	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		if (!(this.gauche.returnType.equals("int") && this.droite.returnType.equals("int"))){
			throw new AnalyseSemantiqueException("Ligne " + this.noLigne + " : Opération arithmetique "+ this.operateur() +" doit être appliqué sur des entiers");
		}
	}
    
}
