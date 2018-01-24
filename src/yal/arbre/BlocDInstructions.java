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
		sb.append("end:\n");
		sb.append("move $v1, $v0      # copie de v0 dans v1 pour permettre les tests de plic0\n");
		sb.append("li $v0, 10         # retour au système\n");
		sb.append("syscall");
		return sb.toString();
	}

}
