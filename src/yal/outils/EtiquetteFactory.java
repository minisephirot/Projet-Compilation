package yal.outils;

/*
 * Création du singleton qui gère la création d'étiquette
 */


public class EtiquetteFactory {

	/*
	 * Instance du singleton
	 */
	private static EtiquetteFactory instance = new EtiquetteFactory();
	
	/*
	 * Nombre d'étiquette si alors sinon créé
	 */
	private int indexSi;
	
	/*
	 * Nombre d'étiquette print créé
	 */
	private int indexPrint;
	
	/*
	 * Vérifie si des divisions sont utilisées
	 */
	private boolean indexDiv0;
	
	/*
	 * Initialise les compteurs à 0 et à false
	 */
	private EtiquetteFactory() {
		indexSi = 0;
		indexPrint = 0;
		indexDiv0 = false;
	};
	
	public static EtiquetteFactory getInstance() {
		return instance;
	}

	public int getIndexSi() {
		return indexSi;
	}
	
	public int getIndexPrint() {
		return indexPrint;
	}

	public boolean getIndexDiv0() {
		return indexDiv0;
	}

	/*
	 * Met la variable à true si une division est utilisée mais ne peut pas
	 * être remis à false après coup
	 */
	public void setIndexDiv0() {
		this.indexDiv0 = true;
	}
	
	public void addIndexSi() {
		indexSi++;
	}
	
	public void addIndexPrint() {
		indexPrint++;
	}
}
