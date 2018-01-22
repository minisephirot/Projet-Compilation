package yal.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {

	protected AnalyseSemantiqueException(String m) {
		super("ERREUR SEMANTIQUE :\n\t" + m) ;
	}

}
