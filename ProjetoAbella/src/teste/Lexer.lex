package teste;
import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
    private PascalToken createToken(String name, String value) {
	return new PascalToken( name, value, yyline, yycolumn);
	}
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace    = {LineTerminator} | [ \t\f]

Digit          = [0-9]
Number         = {Digit} {Digit}*
Letter         = [a-zA-Z] 

%%

<YYINITIAL> { 
    {Number}        {createToken("number", yytext()); return symbol(Sym.NUMBER, new Integer(Integer.parseInt(yytext()))); }

    "+"             {createToken("+", yytext());  return symbol(Sym.PLUS); }    
    "-"             {createToken("-", yytext());  return symbol(Sym.MINUS); }
    "*"             {createToken("*", yytext());  return symbol(Sym.TIMES); }
    "/"             {createToken("/", yytext());  return symbol(Sym.DIVIDED); }

    "("             {createToken("\(", yytext());  return symbol(Sym.LPAREN); }
    ")"             {createToken("\)", yytext());  return symbol(Sym.RPAREN); }

    ";"             {createToken("\;", yytext());  return symbol(Sym.SEMI); }

    {WhiteSpace} {}
}

<<EOF>>             { return symbol( Sym.EOF ); }

[^]                 { throw new Error("Illegal character <" + yytext() + ">");}