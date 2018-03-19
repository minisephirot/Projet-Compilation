package yal.arbre;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

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
    
    public void ajouterDebut(ArbreAbstrait a) {
		listeExpr.add(0, a);	
    }
    
    public int nb() {
    		return listeExpr.size();
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
	public boolean isRetourne(boolean bloc) {
		boolean retourne = false;
		for(ArbreAbstrait arbreAbstrait : listeExpr) {
			if(arbreAbstrait.isRetourne(bloc))
				retourne = true;
		}
		
		return retourne;
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();

		// Génération de l'arbre en MIPS
		for (ArbreAbstrait arbreAbstrait : listeExpr) {
			sb.append(arbreAbstrait.toMIPS());
		}

		return sb.toString();
	}

}
