package yal.arbre.decoration;

import yal.arbre.ArbreAbstrait;

public class AllocationVar extends ArbreAbstrait {
	
	int decalage;

	public AllocationVar(int no, int decalage) {
		super(no);
		this.decalage = decalage;
	}

	@Override
	public void verifier() {
		// Rien a vérifier
	}

	@Override
	public String toMIPS() {
		return "addi $sp, $sp, " + decalage + "\n";
	}

}
