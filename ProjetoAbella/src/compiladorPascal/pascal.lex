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

palavraParserIntToStr		= [Ii][Nn][Tt][Tt][Oo][Ss][Tt][Rr]
palavraParserStrToInt		= [Ss][Tt][Rr][Tt][Oo][Ii][Nn][Tt]
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

palavrasBranco 			= [\n| |\t]

igual				= (":=")
diferente			= [\<][\>]

delimitadores		= (":" | ";" | "."| ","| "=")

vetor		= ("[" | "]")
number		= ("#")

abreChaves       	= [\{]
fechaChaves      	= [\}]


identificador		= [A-Za-z][A-Za-z_0-9]*
abreParenteses      = ("(")
fechaParenteses     = (")")


aspasSimples = ("\'")

terminaLinha = [\r|\n|\r\n]
caracteresTexto = [A-Z|a-z|_|0-9|á|à|â|ã|é|è|ê|í|ï|ó|ô|õ|ö|ú|ç|ñ|Á|À|Â|Ã|É|È|ê|Ê|Í|Ï|Ó|Ô|Õ|Ö|Ú|û|Û|ü|Ü|Ç|Ñ|!|?|º|ª|=|(|)|,|\.|\-|\+|\*|\\|\"|\`|\{|\}|\&|\¨|\§|\¬|\¢|\¹|\²|\³|\£|\´|\~|\°|\r|\n|\r\n|\/|\s|\t|\;|\#|\:|\@|\[|\]|\<|\>|\^|\|]

naoAspas   	= [^\}]

comment_body   	 	= {naoFechaChaves}*
naoFechaChaves   	= [^}]
comment_Linha  	 	= {naoTerminaLinha}*
naoTerminaLinha   	= [^\r|\n|\r\n]
comentario_1		= {abreChaves}{comment_body}*{fechaChaves}
comentario_2		= "/*" [^*] ~"*/" | "/*" "*"+ "/"
comentario_3		= [/]{2,2} {comment_Linha}*{terminaLinha}
comentario_4		= "(*" [^*] ~"*)" | "(*" "*"+ ")"

variavel			= {palavraString}|{palavraInteger}

texto				= {aspasSimples}{caracteresTexto}*{aspasSimples}

erro				= {palavraRaise}{palavrasBranco}*{palavraException}{palavrasBranco}*[.]{palavrasBranco}*{palavraCreate}

SqlAdd				= {palavraSQL}{palavrasBranco}*[.]{palavrasBranco}*{palavraADD}

metodo				= ({palavraProcedure}|{palavraFunction}){palavrasBranco}*{identificador}{palavrasBranco}*[\.]{palavrasBranco}*{identificador}{palavrasBranco}*	
variaveisMetodo		= ({abreParenteses}	 ({palavrasBranco}* {identificador}*{palavrasBranco}* {identificador}*{palavrasBranco}*[\:]*{palavrasBranco}* {variavel}*{palavrasBranco}*[,]*)* 	{fechaParenteses})*
condicaoIf					= {palavraIf} * {palavraThen}

program = "program"

%%

{igual}			{ return new PascalToken( "igual", yytext() ); }
{diferente}			{ return new PascalToken( "diferente", yytext() ); }

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
{porcentagem}			{ return new PascalToken( "porcentagem", yytext() ); }

{number}			{ return new PascalToken( "number", yytext() ); }
{real}			{ return new PascalToken( "numero real", yytext() ); }
{integer}		{ return new PascalToken( "integer", yytext() ); }
{aspasSimples}			{ return new PascalToken( "abreImpressao", yytext() ); }
{abreParenteses}			{ return new PascalToken( "abreParenteses", yytext() ); }
{fechaParenteses}			{ return new PascalToken( "fechaParenteses", yytext() ); }

{texto}			{ return new PascalToken( "texto", yytext() ); }
{vetor}			{ return new PascalToken( "vetor", yytext() ); }

{palavraBegin}		{ return new PascalToken( "begin", yytext() ); }
{palavraEnd} 		{ return new PascalToken( "end", yytext() ); }
{palavraInteger} 		{ return new PascalToken( "inteiro", yytext() ); }
{palavraString} 		{ return new PascalToken( "string", yytext() ); }
{palavraParserIntToStr} 		{ return new PascalToken( "IntToStr", yytext() ); }
{palavraParserStrToInt} 		{ return new PascalToken( "StrToInt", yytext() ); }

{metodo}			{ return new PascalToken( "metodo", yytext() ); }
{variaveisMetodo}	{ return new PascalToken( "variaveisMetodo", yytext() ); }


{palavraIf}		{ return new PascalToken( "if", yytext() ); }
{palavraThen}		{ return new PascalToken( "then", yytext() ); }

{palavraElse}		{ return new PascalToken( "else", yytext() ); }

{terminaLinha}		{ return createToken("fimLinha", yytext()); }

{comentario_1}  { return createToken("comentario1", yytext()); }
{comentario_2}  { return createToken("comentario2", yytext()); }
{comentario_3}  { return createToken("comentario3", yytext()); }
{comentario_4}  { return createToken("comentario4", yytext()); }

{identificador}	{ return createToken("ID", yytext()); }
{delimitadores}		{ return createToken("delimitadores", yytext()); }

{erro}  { return createToken("raise error", yytext()); }
{program} { return createToken(yytext(), "");} 
{palavrasBranco} { /**/ }


{SqlAdd} { return createToken("consulta", yytext()); }


. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }
