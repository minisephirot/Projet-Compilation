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

idf = [a-zA-Z_]\w*
csteE = [0-9]+
csteB = "vrai" | "faux"
csteC = \"([^\"]+|(\"\"))+\"

commentaireSlashSlash = [/][/].*

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%

"+"                	{ return symbol(CodesLexicaux.PLUS); }
"-"                	{ return symbol(CodesLexicaux.MOINS); }
"*"                	{ return symbol(CodesLexicaux.MULT); }
"/"                	{ return symbol(CodesLexicaux.DIV); }

";"						{ return symbol(CodesLexicaux.POINTVIRGULE); }
"="						{ return symbol(CodesLexicaux.EGAL); }
"=="                    { return symbol(CodesLexicaux.EGALEGAL); }
"!="                    { return symbol(CodesLexicaux.DIFF); }
"<"                	{ return symbol(CodesLexicaux.INF); }
">"                	{ return symbol(CodesLexicaux.SUP); }

"et"                	{ return symbol(CodesLexicaux.ET); }
"ou"                	{ return symbol(CodesLexicaux.OU); }
"non"                	{ return symbol(CodesLexicaux.NON); }
"programme"			{ return symbol(CodesLexicaux.PROGRAMME); }
"entier"				{ return symbol(CodesLexicaux.ENTIER); }
"debut"				{ return symbol(CodesLexicaux.DEBUT); }
"fin"				{ return symbol(CodesLexicaux.FIN); }
"ecrire"				{ return symbol(CodesLexicaux.ECR); }
"tantque"                 { return symbol(CodesLexicaux.TANTQUE); }
"repeter"                 { return symbol(CodesLexicaux.REPETER); }
"fintantque"             { return symbol(CodesLexicaux.FINTANTQUE); }
"si"                      { return symbol(CodesLexicaux.SI); }
"alors"                  { return symbol(CodesLexicaux.ALORS); }
"sinon"                  { return symbol(CodesLexicaux.SINON); }
"fsi"                    { return symbol(CodesLexicaux.FSI); }
"lire"                   { return symbol(CodesLexicaux.LIRE); }
"fonction"				 { return symbol(CodesLexicaux.FONCTION); }
"retourne"				 { return symbol(CodesLexicaux.RETOURNE); }


"("                	{ return symbol(CodesLexicaux.PAROUV); }
")"                	{ return symbol(CodesLexicaux.PARFER); }

{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{csteC}					{ return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }
{idf}					{ return symbol(CodesLexicaux.IDF, yytext()); }

{espace}                { }

{commentaireSlashSlash}	{}

{espace}                { }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
