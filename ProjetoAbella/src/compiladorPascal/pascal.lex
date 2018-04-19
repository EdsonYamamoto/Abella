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

palavraBegin				= [Bb][Ee][Gg][Ii][Nn]
palavraEnd					= [Ee][Nn][Dd]
palavraInteger				= [Ii][Nn][Tt][Ee][Gg][Ee][Rr]
palavraString				= [Ss][Tt][Rr][Ii][Nn][Gg]
palavraIf					= [Ii][Ff]
palavraThen					= [Tt][Hh][Ee][Nn]
palavraElse					= [Ee][Ll][Ss][Ee]
palavraProcedure			= [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]
palavraFunction				= [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn]
palavraRaise				= [Rr][Aa][Ii][Ss][Ee]
palavraException			= [Ee][Xx][Cc][Ee][Pp][Tt][Ii][Oo][Nn]
palavraCreate				= [Cc][Rr][Ee][Aa][Tt][Ee]
palavraNot					= [Nn][Oo][Tt]
palavraSQL					= [Ss][Qq][Ll]
palavraADD					= [Aa][Dd][Dd]

integer 			= 0|[1-9][0-9]*
real 				= ((\+|-)?([0-9]+)(\.[0-9]+)?)|((\+|-)?\.?[0-9]+)

mais				= "+"
menos				= "-"
multiplica			= "*"
divide				= "/"
maior				= ">"
menor				= "<"
maiorIgual			= ">="
menorIgual			= "<="
expoente			= "^"
arrouba				= "@"
cifrao				= "$"
porcentagem			= "%"

brancos 			= [\n| |\t]

igual				= (":=")
delimitadores		= (":" | ";" | "."| ","| "=")

vetor		= ("[" | "]")
number		= ("#")


identificadores		= [A-Za-z_][A-Za-z_0-9]*
abreParenteses      = ("(")
fechaParenteses     = (")")


aspasSimples = ("\'")

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n][A-Za-z0-9_áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ!?ºª=(),.]*
naoAspas   	= [^}]


comment_body   	 	= {nonrightbrace}*

leftbrace       	= \{
rightbrace      	= \}


nonrightbrace   	= [^}]


comentario_1		= {leftbrace}{comment_body}{rightbrace}
comentario_2		= "/*" [^*] ~"*/" | "/*" "*"+ "/"
comentario_3		= [/]{2,2} {InputCharacter}* {LineTerminator}
comentario_4		= "(*" [^*] ~"*)" | "(*" "*"+ ")"

texto				= {aspasSimples}{InputCharacter}{aspasSimples}

erro				= {palavraRaise}[ ]{palavraException}*[.]{palavraCreate}*

SqlAdd				= {palavraSQL}[.]{palavraADD}

metodo				= {palavraProcedure}*{palavraFunction}* {InputCharacter}*[.]{InputCharacter}*

program = "program"

%%

{mais}				{ return new PascalToken( "soma", yytext() ); }
{menos}				{ return new PascalToken( "subtracao", yytext() ); }
{multiplica}		{ return new PascalToken( "multiplicar", yytext() ); }
{divide}			{ return new PascalToken( "dividir", yytext() ); }

{maior}				{ return new PascalToken( "maior", yytext() ); }
{menor}				{ return new PascalToken( "menor", yytext() ); }
{maiorIgual}		{ return new PascalToken( "igual ou maior", yytext() ); }
{menorIgual}		{ return new PascalToken( "igual ou menor", yytext() ); }

{expoente}		{ return new PascalToken( "expoente", yytext() ); }
{arrouba}		{ return new PascalToken( "arrouba", yytext() ); }
{cifrao}			{ return new PascalToken( "cifrao", yytext() ); }
{porcentagem}			{ return new PascalToken( "porrcentagem", yytext() ); }

{number}			{ return new PascalToken( "number", yytext() ); }
{real}			{ return new PascalToken( "numero real", yytext() ); }
{integer}		{ return new PascalToken( "integer", yytext() ); }
{aspasSimples}			{ return new PascalToken( "abreImpressao", yytext() ); }
{abreParenteses}			{ return new PascalToken( "abreParenteses", yytext() ); }
{fechaParenteses}			{ return new PascalToken( "fechaParenteses", yytext() ); }

{texto}			{ return new PascalToken( "texto", yytext() ); }
{igual}			{ return new PascalToken( "igual", yytext() ); }
{vetor}			{ return new PascalToken( "vetor", yytext() ); }

{palavraBegin}		{ return new PascalToken( "begin", yytext() ); }
{palavraEnd} 		{ return new PascalToken( "end", yytext() ); }
{palavraInteger} 		{ return new PascalToken( "inteiro", yytext() ); }
{palavraString} 		{ return new PascalToken( "string", yytext() ); }

{palavraIf}		{ return new PascalToken( "if", yytext() ); }
{palavraThen}		{ return new PascalToken( "then", yytext() ); }
{palavraElse}		{ return new PascalToken( "else", yytext() ); }

{LineTerminator}		{ return createToken("fimLinha", yytext()); }

{comentario_1}  { return createToken("comentario1", yytext()); }
{comentario_2}  { return createToken("comentario2", yytext()); }
{comentario_3}  { return createToken("comentario3", yytext()); }
{comentario_4}  { return createToken("comentario4", yytext()); }

{identificadores}	{ return createToken("identificadores", yytext()); }
{delimitadores}		{ return createToken("delimitadores", yytext()); }

{erro}  { return createToken("raise error", yytext()); }
{program} { return createToken(yytext(), "");} 
{brancos} { /**/ }


{SqlAdd} { return createToken("consulta", yytext()); }


. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }
