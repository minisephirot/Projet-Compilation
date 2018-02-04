package yal.arbre.expression.constante;

/**
 * 3 dÃ©c. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
        this.setReturnType("int");
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# constante entière\n");
		sb.append("li $v0, ");
		sb.append(this.cste);
		sb.append("\n");
		return sb.toString();
	}
}
