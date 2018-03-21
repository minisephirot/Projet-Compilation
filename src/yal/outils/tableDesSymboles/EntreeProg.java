package yal.outils.tableDesSymboles;

public class EntreeProg extends Entree {
	
	private int nbParam;
		
	public EntreeProg(String idf, int noLigne, int nbParam) {
		super(idf, noLigne);
		this.nbParam = nbParam;
	}
	
	public int getNbParam() {
		return nbParam;
	}
}
