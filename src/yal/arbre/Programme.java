package yal.arbre;

import java.util.ArrayList;

import yal.outils.EtiquetteFactory;
import yal.outils.tableDesSymboles.TableDesSymboles;

public class Programme extends ArbreAbstrait {
	
	private ArrayList<BlocDInstructions> listeBlocs;

	public Programme(int no) {
		super(no);
		listeBlocs = new ArrayList<>();
	}
	
	public void ajouterBloc(BlocDInstructions a) {
		listeBlocs.add(a);
	}

	@Override
	public void verifier() {
		
		// Décore les bloc avec la gestion des variables
		// Appelle Verifier sur tous les blocs
		for (BlocDInstructions blocDInstructions : listeBlocs) {
			TableDesSymboles.getInstance().decorerArbre(blocDInstructions);
			blocDInstructions.verifier();
		}
		
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		// Ajoute les chaines à afficher
		sb.append(EtiquetteFactory.getInstance().ecrireChaines());
		
	    // Le début du programme avec la sauvegarde de $sp
	    sb.append(".text\n");
	    sb.append("main:\n");
	    sb.append("# init variable repérer la zone des variables\n");
	    sb.append("move $s7, $sp\n");	 

		// Ajoute le toMips de tous les blocs d'instruction
		for (ArbreAbstrait ArbreAbstrait : listeBlocs) {
			sb.append(ArbreAbstrait.toMIPS());
		}
		
	    // La fin pour quitter proprement le programme
	    sb.append("end:\n");	 
	    sb.append("li $v0, 10         # retour au système\n");	 
	    sb.append("syscall\n");
	    
	    // Affiche le test de div par zéro
	    sb.append(EtiquetteFactory.getInstance().ecrireTestDiv0());

		return sb.toString();
	}

}
