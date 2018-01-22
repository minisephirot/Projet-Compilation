package yal.arbre;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArbreAbstrait expr ;
    
    public BlocDInstructions(int n) {
        super(n) ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        expr = a ;
    }
    
    @Override
    public String toString() {
        return expr.toString() ;
    }

	@Override
	public void verifier() {
		expr.verifier();		
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("main:\n");
		sb.append("# init variable repérer la zone des variables\n");
		sb.append("move $s7, $sp\n");
		sb.append(expr.toMIPS());
		sb.append("\n");
		return sb.toString();
	}

}
