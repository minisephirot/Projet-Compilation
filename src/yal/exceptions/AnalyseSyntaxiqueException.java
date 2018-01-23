package yal.exceptions;

/**
 * 10 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

@SuppressWarnings("serial")
public class AnalyseSyntaxiqueException extends AnalyseException {
 
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }

}
