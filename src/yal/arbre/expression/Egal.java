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
		if ((
			this.gauche instanceof ConstanteBool ||
			this.gauche instanceof Different ||
			this.gauche instanceof Egal ||
			this.gauche instanceof EtLogique ||
			this.gauche instanceof NonLogique ||
			this.gauche instanceof OuLogique
			)&&(
			this.droite instanceof ConstanteBool ||
			this.droite instanceof Different ||
			this.droite instanceof Egal ||
			this.droite instanceof EtLogique ||
			this.droite instanceof NonLogique ||
			this.droite instanceof OuLogique
		)){}
		else{throw new AnalyseSemantiqueException("Ligne "+this.noLigne+" : Comparaison doit être Bool|Bool");}
	}
}
