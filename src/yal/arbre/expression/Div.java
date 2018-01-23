package yal.arbre.expression;

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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# division gauche droite\n");
		sb.append("div $v0, $t8, $v0");
		return sb.toString();
	}

	@Override
	public void verifier() {
		// TODO Auto-generated method stub
		
	}
}
