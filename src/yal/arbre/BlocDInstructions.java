package yal.arbre;

import java.util.ArrayList;

import yal.outils.EtiquetteFactory;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> listeExpr ;
    
    public BlocDInstructions(int n) {
        super(n) ;
        listeExpr = new ArrayList<>();
    }
    
    public void ajouter(ArbreAbstrait a) {
    	listeExpr.add(a) ;
    }
    
    @Override
    public String toString() {
    	return listeExpr.toString() ;
    }

	@Override
	public void verifier() {
		for (ArbreAbstrait arbreAbstrait : listeExpr) {
			arbreAbstrait.verifier();
		}
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
		for (ArbreAbstrait arbreAbstrait : listeExpr) {
			arbreAbstrait.toMIPS();
		}
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
		}
		if (EtiquetteFactory.getInstance().getIndexPrint() > 0){
			for (String s : EtiquetteFactory.getInstance().getPrintArray()) 
				sb.insert(0, s);
		}
		sb.insert(0, ".data\n");
		return sb.toString();
	}

}
