package yal.exceptions;

/**
 * Singleton qui permet de stocker toutes les erreurs sémantiques
 * @author Arnaud
 *
 */
public class ListeErreursSemantiques {
	
	private static ListeErreursSemantiques instance = new ListeErreursSemantiques();
	private StringBuilder listeException;
	
	private ListeErreursSemantiques() {
		listeException = new StringBuilder();
	}
	
	/**
	 * @return L'instance de la classe
	 */
	public static ListeErreursSemantiques getInstance() {
		return instance;
	}
	
	/**
	 * Ajoute une erreur sémantique à la liste
	 * @param erreur à ajouter
	 */
	public void addErreur(int noLigne, String erreur) {
		listeException.append("\tLigne ");
		listeException.append(noLigne);
		listeException.append(" : ");
		listeException.append(erreur);
		listeException.append("\n");
	}
	
	/**
	 * @return vrai si il existe au moins une erreur
	 */
	public boolean isErreurs() {
		return listeException.length() != 0;
	}
	
	/**
	 * Permet de throw une exception sémantique si nécessaire
	 */
	public void throwAll() {
		if (isErreurs()) {
			throw new AnalyseSemantiqueException(listeException.toString());
		}
	}

}
