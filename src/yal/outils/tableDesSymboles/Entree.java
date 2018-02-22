package yal.outils.tableDesSymboles;

public abstract class Entree {
	
	private String idf;
	private int noLigne;
	
	public Entree(String i, int noLigne) {
		idf = i;
		this.noLigne = noLigne;
		if (idf == null)
		System.out.println(noLigne);
	}

	public String getIdf() {
		return idf;
	}

	public void setIdf(String idf) {
		this.idf = idf;
	}
	
	public int getNoLigne() {
		return noLigne;
	}
	
	/**
	 * @return Retourne le type de la varriable (ici on gère que des entiers)
	 */
	public String getType() {
		return "int";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj instanceof Entree && ((Entree) obj).getIdf().equals(idf)) {
			return true;
		} else
			return false;
	}
	
	@Override
	public int hashCode() {
		// Ajouter le truc de bloc dans le hash
		return idf.hashCode();
	}

}
