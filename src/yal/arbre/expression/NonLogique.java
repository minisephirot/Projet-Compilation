package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class NonLogique extends Unaire {
    
    public NonLogique(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return " non " ;
    }

	@Override
	public void verifier() {
		this.expression.verifier();
		if (
			this.expression instanceof ConstanteBool ||
			this.expression instanceof Different ||
			this.expression instanceof Egal ||
			this.expression instanceof EtLogique ||
			this.expression instanceof NonLogique ||
			this.expression instanceof OuLogique
		){}
		else{throw new AnalyseSemantiqueException("Ligne "+this.noLigne+" : Non Logique doit être appliqué sur un Bool");}
	}
    
}
