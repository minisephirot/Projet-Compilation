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

%state string
%state commentaire

commentaireSlashSlash = [/][/].*
commentaireSlashEtoile = [/][*]
commentaireEtoileSlash = [*][/]

csteE = [0-9]+
csteB = "vrai" | "faux"
csteC = \"([^\"]+|(\"\"))((\"\")*[^\"]*)*\"

prog = "programme"
entier = "entier"
debut = "debut"
fin = "fin"

stringCircon = ["]
stringCirconCircon = ["]["]


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

"("                	{ return symbol(CodesLexicaux.PAROUV); }
")"                	{ return symbol(CodesLexicaux.PARFER); }

{csteE}      	        { return symbol(CodesLexicaux.CONSTANTEINT, yytext()); }
{csteB}      	        { return symbol(CodesLexicaux.CONSTANTEBOOL, yytext()); }
{csteC}					{ return symbol(CodesLexicaux.CONSTANTECHAINE, yytext()); }

{prog} 					{ return symbol(CodesLexicaux.PROG, yytext());}
{entier} 				{ return symbol(CodesLexicaux.ENTIER, yytext());}
{debut} 				{ return symbol(CodesLexicaux.DEBUT, yytext());}
{fin}	 				{ return symbol(CodesLexicaux.FIN, yytext());}
{prog}					{ return symbol(CodesLexicaux.PROGRAMME, yytext()); }

<YYINITIAL>{commentaireSlashEtoile} { yybegin(commentaire); }
<YYINITIAL>{commentaireSlashSlash} {}
<commentaire> {commentaireEtoileSlash} { yybegin(YYINITIAL); }

{espace}                { }

.                       { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
