package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Plus extends BinaireArithmetique {

    public Plus(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " + " ;
    }

	@Override
	public void verifier() {
		// Test si gauche et droite sont des entiers
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(evluationExpressions());
		sb.append("# addition gauche droite\n");
		sb.append("add $v0, $v0, $t8");
		return sb.toString();
	}

}
