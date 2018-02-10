package yal.arbre;

import java.util.ArrayList;
import java.util.Iterator;

import yal.outils.EtiquetteFactory;

public class Programme extends ArbreAbstrait {
	
	private ArrayList<BlocDInstructions> listeBlocs;

	public Programme(int no) {
		super(no);
		listeBlocs = new ArrayList<>();
	}
	
	public void ajouter(BlocDInstructions b) {
		listeBlocs.add(b);
	}

	@Override
	public void verifier() {
		for (BlocDInstructions blocDInstructions : listeBlocs) {
			blocDInstructions.verifier();
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		// Les data du début
		sb.append(".data\n");
		// On ajoute la chaine d'erreur d'une division par 0 si besoin
		boolean hasDivBy0 = EtiquetteFactory.getInstance().hasDivBy0();
		if (hasDivBy0) {
			sb.append("errDiv0:     .asciiz \" ERREUR EXECUTION : Division par 0 interdite\\n\"\n");
		}
		// Ajoute les chaines à afficher
		Iterator<String> it = EtiquetteFactory.getInstance().getStrings();
		while (it.hasNext()) {
			sb.append(it.next());
		}
		
		// Le début du programme avec la sauvegarde de $sp
		sb.append(".text\n");
		sb.append("main:\n");
		sb.append("# init variable repérer la zone des variables\n");
		sb.append("move $s7, $sp\n");

		// Ajoute le toMips de tous les blocs d'instruction
		for (BlocDInstructions blocDInstructions : listeBlocs) {
			sb.append(blocDInstructions.toMIPS());
		}
		
		// La fin pour quitter proprement le programme
		sb.append("end:\n");
		sb.append("move $v1, $v0      # copie de v0 dans v1 pour permettre les tests de plic0\n");
		sb.append("li $v0, 10         # retour au système\n");
		sb.append("syscall\n");
		
		// Génère le code qui affiche une erreur si on divise par zéro
		if (hasDivBy0) {
			sb.append("# La gestion d'une division par 0\n");
			sb.append("divByZero:\n");
			sb.append("li $v0, 4\n");
			sb.append("la $a0, errDiv0\n");
			sb.append("syscall\n");
			sb.append("b end\n");
		}
				
		return sb.toString();
	}

}
