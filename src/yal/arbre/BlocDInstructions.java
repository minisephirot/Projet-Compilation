package yal.arbre;

import yal.outils.EtiquetteFactory;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArbreAbstrait expr ;
    private static StringBuilder sb;
    
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
		BlocDInstructions.setSb(new StringBuilder());
		// Les data du début
		getSb().append(".text\n");
		getSb().append("main:\n");
		getSb().append("# init variable repérer la zone des variables\n");
		getSb().append("move $s7, $sp\n");
		// Génération de l'arbre en MIPS
		getSb().append(expr.toMIPS());
		// La fin pour quitter proprement le programme
		getSb().append("end:\n");
		getSb().append("move $v1, $v0      # copie de v0 dans v1 pour permettre les tests de plic0\n");
		getSb().append("li $v0, 10         # retour au système\n");
		getSb().append("syscall\n");
		// Génère le code qui affiche une erreur si on divise par zéro
		if (EtiquetteFactory.getInstance().getIndexDiv0()) {
			getSb().append("# La gestion d'une division par 0\n");
			getSb().append("divByZero:\n");
			getSb().append("li $v0, 4\n");
			getSb().append("la $a0, errDiv0\n");
			getSb().append("syscall\n");
			getSb().append("b end\n");
			
			getSb().insert(0, "errDiv0:     .asciiz \" ERREUR EXECUTION : Division par 0 interdite\\n\"\n");
		}
		getSb().insert(0, ".data\n");
		return getSb().toString();
	}

	public static StringBuilder getSb() {
		return sb;
	}

	public static void setSb(StringBuilder sb) {
		BlocDInstructions.sb = sb;
	}

}
