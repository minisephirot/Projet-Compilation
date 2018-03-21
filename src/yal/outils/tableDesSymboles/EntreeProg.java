package yal.outils.tableDesSymboles;

public class EntreeProg extends Entree {
	
	int numParam;
		
	public EntreeProg(String idf, int noLigne, int numParam) {
		super(idf, noLigne);
		this.numParam = numParam;
	}
}
