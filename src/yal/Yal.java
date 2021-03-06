package yal ;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;
import yal.exceptions.ListeErreursSemantiques;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 24 mars 2015 
 * 
 * @author brigitte wrobel-dautcourt
 */

public class Yal {
    
    public Yal(String fichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
            //System.err.println("expression stockée dans l'arbre : " + arbre);
            
            // à écrire pour yal0
            arbre.verifier() ;
            
            // Appelle le singleton qui déclanche une erreur contentnant la liste de toutes les erreurs
            // sémantiques
            ListeErreursSemantiques.getInstance().throwAll();
            
            // Ecriture du code mips dans le fichier [nomEntrée].mips
            String fichierSortie = fichier.substring(0, fichier.length() - 3);
            fichierSortie = fichierSortie + "mips";
            BufferedWriter buff = new BufferedWriter(new FileWriter(new File(fichierSortie)));
            buff.write(arbre.toMIPS());
            buff.close();
            
            System.out.println("COMPILATION OK");
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + fichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new Yal(args[0]) ;
    }
    
}
