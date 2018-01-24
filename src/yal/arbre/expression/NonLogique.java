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
        this.returntype = "bool";
    }

    @Override
    public String operateur() {
        return " non " ;
    }

	@Override
	public void verifier() {
		if (!this.expression.returntype.equals("bool")){
			throw new AnalyseSemantiqueException("Ligne " + this.noLigne + " : Non Logique doit être appliqué sur un booléen");
		}
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("#non logique\n");
		//Impossible d'utiliser not qui est une macro inversant tout les bits, on utilise un non exclusif avec 1
		sb.append("xori $v0, $v0, 1\n");
		return sb.toString();
	}
    
}
