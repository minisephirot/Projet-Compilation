package yal.arbre.expression.constante;

/**
 * 3 dÃ©c. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
        this.setReturnType("bool");
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# constante bool\n");
		if (cste.equals("vrai"))
			sb.append("li $v0, 1\n");
		else
			sb.append("li $v0, 0\n");
		return sb.toString();
	}
}
