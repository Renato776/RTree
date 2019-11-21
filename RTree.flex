/* JFlex example: part of Java language lexer specification */
package com.rTree;
import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%line
%column
%caseless

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

%}

DATO   = "<" [^*] ~">"
IDENTIFIER = [a-zA-Z_][0-9a-zA-Z_]*
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
%%

/* keywords */

"{"           { printing.lexicalDebugg("LEFT_BRACE", yytext(), yyline, yycolumn); return new Symbol(sym.LEFT_BRACE,
yycolumn,yyline,yytext()); }
"}"           { printing.lexicalDebugg("RIGHT_BRACE", yytext(), yyline, yycolumn); return new Symbol(sym.RIGHT_BRACE,
yycolumn,yyline,yytext()); }
"["           { printing.lexicalDebugg("LEFT_BRACKET", yytext(), yyline, yycolumn); return new Symbol(sym.LEFT_BRACKET,
yycolumn,yyline,yytext()); }
"]"           { printing.lexicalDebugg("RIGHT_BRACKET", yytext(), yyline, yycolumn); return new Symbol(sym.RIGHT_BRACKET,
yycolumn,yyline,yytext()); }
"|"           { printing.lexicalDebugg("PIPE", yytext(), yyline, yycolumn); return new Symbol(sym.PIPE,yycolumn,yyline,yytext()); }
":"           { printing.lexicalDebugg("DOUBLE_COLON", yytext(), yyline, yycolumn); return new Symbol(sym.DOUBLE_COLON,
yycolumn,yyline, yytext()); }

{DATO}        {printing.lexicalDebugg("DATO", yytext(), yyline, yycolumn); return new Symbol(sym.DATO,yycolumn,yyline,
new LToken("dato",yycolumn, yyline, yytext(),true)); }
{IDENTIFIER} {printing.lexicalDebugg("IDENTIFIER", yytext(), yyline, yycolumn); return new Symbol(sym.IDENTIFIER,yycolumn,yyline,
new LToken("id",yycolumn, yyline, yytext(), false)); }

<YYINITIAL> {  
  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}
