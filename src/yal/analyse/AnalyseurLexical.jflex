package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

%state commentaire


idf = [a-zA-Z_]\w*
csteE = [0-9]+
csteB = "vrai" | "faux"
csteC = \"([^\"]+|(\"\"))+\"

prog = "programme"
entier = "entier"
debut = "debut"
fin = "fin"
ecrire = "ecrire"

commentaireSlashSlash = [/][/].*
commentaireSlashEtoile = [/][*]
commentaireEtoileSlash = [*][/]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

<YYINITIAL> "+"                	{ return symbol(CodesLexicaux.PLUS); }
<YYINITIAL> "-"                	{ return symbol(CodesLexicaux.MOINS); }
<YYINITIAL> "*"                	{ return symbol(CodesLexicaux.MULT); }
<YYINITIAL> "/"                	{ return symbol(CodesLexicaux.DIV); }

<YYINITIAL> ";"						{ return symbol(CodesLexicaux.POINTVIRGULE); }
<YYINITIAL> "="						{ return symbol(CodesLexicaux.EGAL); }
<YYINITIAL> "=="                    { return symbol(CodesLexicaux.EGALEGAL); }
<YYINITIAL> "!="                    { return symbol(CodesLexicaux.DIFF); }
<YYINITIAL> "<"                	{ return symbol(CodesLexicaux.INF); }
<YYINITIAL> ">"                	{ return symbol(CodesLexicaux.SUP); }

<YYINITIAL> "et"                	{ return symbol(CodesLexicaux.ET); }
<YYINITIAL> "ou"                	{ return symbol(CodesLexicaux.OU); }
<YYINITIAL> "non"                	{ return symbol(CodesLexicaux.NON); }

<YYINITIAL> "("                	{ return symbol(CodesLexicaux.PAROUV); }
<YYINITIAL> ")"                	{ return symbol(CodesLexicaux.PARFER); }

<YYINITIAL> {csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
<YYINITIAL> {csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
<YYINITIAL> {csteC}					{ return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }
<YYINITIAL> {idf}					{ return symbol(CodesLexicaux.IDF, yytext()); }

<YYINITIAL> {prog}					{ return symbol(CodesLexicaux.PROGRAMME, yytext()); }
<YYINITIAL> {entier} 				{ return symbol(CodesLexicaux.ENTIER, yytext()); }
<YYINITIAL> {debut} 				{ return symbol(CodesLexicaux.DEBUT, yytext()); }
<YYINITIAL> {fin}	 				{ return symbol(CodesLexicaux.FIN, yytext()); }
<YYINITIAL> {ecrire}					{ return symbol(CodesLexicaux.ECR, yytext()); }

<YYINITIAL> {espace}                { }

<YYINITIAL> {commentaireSlashSlash}	{}

<YYINITIAL> {commentaireSlashEtoile}	{ yybegin(commentaire) ; }
<commentaire> {commentaireEtoileSlash} 	{ yybegin(YYINITIAL) ; }

{espace}                { }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
