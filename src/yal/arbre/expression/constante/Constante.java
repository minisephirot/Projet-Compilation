package yal.arbre.expression.constante;

import yal.arbre.expression.Expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

	@Override
	public void verifier() {}
   
    @Override
    public String toString() {
        return cste ;
    }

}
