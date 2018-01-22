package yal.exceptions;

/**
 * 10 déc. 2015
 *
 * @author AGE
 */

public class AnalyseSemantiqueException extends AnalyseException {
 
    public AnalyseSemantiqueException(String m) {
        super("ERREUR SEMANTIQUE :\n\t" + m) ;
    }

}
