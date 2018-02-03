package yal.arbre.expression;

import yal.outils.FabriqueAEtiquette;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Div extends BinaireArithmetique {

    public Div(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " / ";
    }
    
	@Override
	public void verifier() {
		super.verifier();
	}
    
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# test si on divise par 0\n");
		sb.append("beqz $v0, divByZero\n");
		// Appelle la fabrique à étiquette pour générer le code d'affichage d'erreur en MIPS
		FabriqueAEtiquette.getInstance().setIndexDiv0();
		sb.append("# division gauche droite\n");
		sb.append("div $v0, $t8, $v0\n");
		return sb.toString();
	}
}
