package yal.arbre.expression;

public class Appel extends Expression {

	private IDF idf;
	
	public Appel(IDF i,int n) {
		super(n);
		idf = i;
		returnType = "int";
	}

	@Override
	public void verifier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

}
