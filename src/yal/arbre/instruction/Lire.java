package yal.arbre.instruction;

public class Lire extends Instruction {

	private String idf;
	
	public Lire(String i, int no) {
		super(no);
		idf = i;
	}

	@Override
	public void verifier() {
		
	}

	@Override
	public String toMIPS() {
		return null;
	}

}
