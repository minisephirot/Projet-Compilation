package yal.arbre;

import yal.outils.EtiquetteFactory;

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
		// Les data du début
		sb.append(".text\n");
		sb.append("main:\n");
		sb.append("# init variable repérer la zone des variables\n");
		sb.append("move $s7, $sp\n");
		// Génération de l'arbre en MIPS
		sb.append(expr.toMIPS());
		// La fin pour quitter proprement le programme
		sb.append("end:\n");
		sb.append("move $v1, $v0      # copie de v0 dans v1 pour permettre les tests de plic0\n");
		sb.append("li $v0, 10         # retour au système\n");
		sb.append("syscall\n");
		// Génère le code qui affiche une erreur si on divise par zéro
		if (EtiquetteFactory.getInstance().getIndexDiv0()) {
			sb.append("# La gestion d'une division par 0\n");
			sb.append("divByZero:\n");
			sb.append("li $v0, 4\n");
			sb.append("la $a0, errDiv0\n");
			sb.append("syscall\n");
			sb.append("b end\n");
			
			sb.insert(0, "errDiv0:     .asciiz \" ERREUR EXECUTION : Division par 0 interdite\\n\"\n");
			sb.insert(0, ".data\n");
		}
		return sb.toString();
	}

}
