package yal.outils;

/*
 * Création du singleton qui gère la création d'étiquette
 */


public class FabriqueAEtiquette {

	/*
	 * Instance du singleton
	 */
	private static FabriqueAEtiquette instance = new FabriqueAEtiquette();
	
	/*
	 * Nombre d'étiquette si alors sinon créé
	 */
	private int indexSi;
	
	/*
	 * Vérifie si des divisions sont utilisées
	 */
	private boolean indexDiv0;
	
	/*
	 * Initialise les compteurs à 0 et à false
	 */
	private FabriqueAEtiquette() {
		indexSi = 0;
		indexDiv0 = false;
	};
	
	public static FabriqueAEtiquette getInstance() {
		return instance;
	}

	public int getIndexSi() {
		return indexSi;
	}

	public boolean getIndexDiv0() {
		return indexDiv0;
	}

	/*
	 * Met la variable à true si une division et utiliser mais ne peut pas
	 * être remis à false une fois que une division est utilisée
	 */
	public void setIndexDiv0() {
		this.indexDiv0 = true;
	}
	
	public void addIndexSi() {
		indexSi++;
	}
}
