package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class OuLogique extends BinaireLogique {

    public OuLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " ou " ;
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
		else{throw new AnalyseSemantiqueException("Ligne "+this.noLigne+" : Ou Logique doit être Bool|Bool");}
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# ou logique gauche droite\n");
		sb.append("or $v0, $t8, $v0");
		return sb.toString();
	}
    
}
