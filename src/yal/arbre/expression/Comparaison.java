package yal.arbre.expression;

import yal.outils.FabriqueAEtiquette;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class Comparaison extends Binaire {
     
    protected Comparaison(Expression gauche, Expression droite) {
        super(gauche, droite);
        this.returnType = "bool";
    }
    
    public int getEtiquette() {
		int etiq = FabriqueAEtiquette.getInstance().getIndexSi();
		FabriqueAEtiquette.getInstance().addIndexSi();
		return etiq;
	}

}
