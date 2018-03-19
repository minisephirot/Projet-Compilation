package yal.arbre;

import yal.arbre.decoration.AllocationVar;
import yal.outils.EtiquetteFactory;

public class Programme extends ArbreAbstrait {

	private BlocDInstructions bloc;

	public Programme(int no, BlocDInstructions blocinstr, BlocDInstructions blocdecl) {
		super(no);
		this.bloc = blocdecl;
		bloc.ajouter(blocinstr);
		
		// Ajoute un noeud qui s'occupe du décalage des variables
		bloc.ajouterDebut(new AllocationVar(no));
	}
	
	public Programme(int no, BlocDInstructions blocinstr) {
		super(no);
		this.bloc = blocinstr;
		
		// Ajoute un noeud qui s'occupe du décalage des variables
		bloc.ajouterDebut(new AllocationVar(no));
	}

	@Override
	public void verifier() {
		// Appelle Verifier sur tous les blocs
		bloc.verifier();
		bloc.isRetourne(false);
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
		sb.append(bloc.toMIPS());

		// La fin pour quitter proprement le programme
		sb.append("end:\n");	 
		sb.append("li $v0, 10         # retour au système\n");	 
		sb.append("syscall\n");

		// Affiche le test de div par zéro
		sb.append(EtiquetteFactory.getInstance().ecrireTestDiv0());

		return sb.toString();
	}

}
