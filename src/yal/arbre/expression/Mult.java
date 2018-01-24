package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Mult extends BinaireArithmetique {

    public Mult(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
  
    @Override
    public String operateur() {
        return " * ";
    }
    
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# multiplication gauche droite\n");
		sb.append("mult $v0, $t8\n");
		sb.append("mflo $v0\n");		
		return sb.toString();
	}
}
