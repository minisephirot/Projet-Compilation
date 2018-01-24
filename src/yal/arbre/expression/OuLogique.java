package yal.arbre.expression;

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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# ou logique gauche droite\n");
		sb.append("or $v0, $t8, $v0\n");
		return sb.toString();
	}
    
}
