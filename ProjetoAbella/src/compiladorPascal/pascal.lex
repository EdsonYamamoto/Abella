package compiladorPascal;

import java_cup.runtime.*;

%%

%{


private PascalToken createToken(String name, String value) {
	return new PascalToken( name, value, yyline, yycolumn);
}

%}

%public
%class LexicalAnalyzer
%type PascalToken
%line
%column

inteiro = 0|[1-9][0-9]*
brancos = [\n| |\t]

igual				= (":=")
delimitadores		= (":" | ";" | ".")
identificadores		= [A-Za-z_][A-Za-z_0-9]*
abreParenteses       = ("(")
fechaParenteses       = (")")
leftbrace       = \{
rightbrace      = \}
comment_body    = {nonrightbrace}*
nonrightbrace   = [^}]
comentario_1	= {leftbrace}{comment_body}{rightbrace}
comentario_2	= "/*" [^*] ~"*/" | "/*" "*"+ "/"
comentario_3	= "//" {InputCharacter}* {LineTerminator}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r|\n|]

iniciaImpressao = ("\"")

program = "program"

%%

"real"			{ return new PascalToken( "real", yytext() ); }
"integer"		{ return new PascalToken( "integer", yytext() ); }
">"			{ return new PascalToken( ">", yytext() ); }
"*"			{ return new PascalToken( "*", yytext() ); }
{iniciaImpressao}			{ return new PascalToken( "abreImpressao", yytext() ); }
{abreParenteses}			{ return new PascalToken( "(", yytext() ); }
{fechaParenteses}			{ return new PascalToken( ")", yytext() ); }
{igual}			{ return new PascalToken( ":=", yytext() ); }
"program" 	{ return new PascalToken( "program", yytext() ); }		
"Var"		{ return new PascalToken( "var", yytext() ); }
"begin"		{ return new PascalToken( "begin", yytext() ); }
"if"		{ return new PascalToken( "if", yytext() ); }
"then"		{ return new PascalToken( "then", yytext() ); }
"else"		{ return new PascalToken( "else", yytext() ); }
"end" 		{ return new PascalToken( "end", yytext() ); }


{LineTerminator}		{ return createToken("fimLinha", yytext()); }

{comentario_1}  { return createToken("comentario1", yytext()); }
{comentario_2}  { return createToken("comentario2", yytext()); }
{comentario_3}  { return createToken("comentario3", yytext()); }

{identificadores}	{ return createToken("identificadores", yytext()); }
{delimitadores}		{ return createToken("delimitadores", yytext()); }

{inteiro} { return createToken("inteiro", yytext()); }
{program} { return createToken(yytext(), "");} 
{brancos} { /**/ }

. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }
