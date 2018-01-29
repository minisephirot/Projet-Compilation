package yal.exceptions;

@SuppressWarnings("serial")
public class AnalyseSemantiqueException extends AnalyseException {

	public AnalyseSemantiqueException(String m) {
		super("ERREUR SEMANTIQUE :\n" + m) ;
	}

}
