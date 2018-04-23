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
/*
Palavras reservadas
*/
palavraParserIntToStr		= [Ii][Nn][Tt][Tt][Oo][Ss][Tt][Rr]
palavraParserStrToInt		= [Ss][Tt][Rr][Tt][Oo][Ii][Nn][Tt]
palavraBegin				= [Bb][Ee][Gg][Ii][Nn]
palavraEnd					= [Ee][Nn][Dd]
palavraInteger				= [Ii][Nn][Tt][Ee][Gg][Ee][Rr]
palavraString				= [Ss][Tt][Rr][Ii][Nn][Gg]
palavraBoolean				= [Bb][Oo][Oo][Ll][Ee][Aa][Nn]
palavraIf					= [Ii][Ff]
palavraThen					= [Tt][Hh][Ee][Nn]
palavraElse					= [Ee][Ll][Ss][Ee]
palavraProcedure			= [Pp][Rr][Oo][Cc][Ee][Dd][Uu][Rr][Ee]
palavraFunction				= [Ff][Uu][Nn][Cc][Tt][Ii][Oo][Nn]
palavraRaise				= [Rr][Aa][Ii][Ss][Ee]
palavraVar					= [Vv][Aa][Rr]
palavraException			= [Ee][Xx][Cc][Ee][Pp][Tt][Ii][Oo][Nn]
palavraCreate				= [Cc][Rr][Ee][Aa][Tt][Ee]
palavraNot					= [Nn][Oo][Tt]
palavraSQL					= [Ss][Qq][Ll]
palavraADD					= [Aa][Dd][Dd]
palavraPrivate				= [Pp][Rr][Ii][Vv][Aa][Tt][Ee]
palavraPublic				= [Pp][Uu][Bb][Ll][Ii][Cc]
palavraShowmessage			= [Ss][Hh][Oo][Ww][Mm][Ee][Ss][Ss][Aa][Gg][Ee]
palavraClear				= [Cc][Ll][Ee][Aa][Rr]
palavraParambyname			= [Pp][Aa][Rr][Aa][Mm][Bb][Yy][Nn][Aa][Mm][Ee]
palavraFieldbyname			= [Ff][Ii][Ee][Ll][Dd][Bb][Yy][Nn][Aa][Mm][Ee]]
palavraArray				= [Aa][Rr][Rr][Aa][Yy]
palavraOf					= [Oo][Ff]
palavraExecSQL				= [Ee][Xx][Ee][Cc][Ss][Qq][Ll]
palavraCafree				= [Cc][Aa][Ff][Rr][Ee][Ee]
palavraQuotedstr			= [Qq][Uu][Oo][Tt][Ee][Dd][Ss][Tt][Rr]
palavraOpen					= [Oo][Pp][Ee][Nn]
palavraUnit					= [Uu][Nn][Ii][Tt]
palavraAsstring				= [Aa][Ss][Ss][Tt][Rr][Ii][Nn][Gg]
palavraAsinteger			= [Aa][Ss][Ii][Nn][Tt][Ee][Gg][Ee][Rr]
palavraTrim					= [Tt][Rr][Ii][Mm]
palavraResult				= [Rr][Ee][Ss][Uu][Ll][Tt]

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
igual				= ":="
expoente			= "^"
arrouba				= "@"
cifrao				= "$"
porcentagem			= "%"

begin				= {palavraBegin}

end					= {palavraEnd}{proximaInstrucao}*[\;]*

palavrasBranco 			= [\n| |\t]

diferente			= [\<][\>]

delimitadores		= (":" | ";" | "."| ",")
simboloIgual				= "="

abreColchetes		= [\[]
fechaColchetes		= [\]]
number		= ("#")

abreChaves       	= [\{]
fechaChaves      	= [\}]


identificador		= [A-Za-z][A-Za-z_0-9]*
abreParenteses      = ("(")
fechaParenteses     = (")")

aspasSimples = ("\'")

terminaLinha = [\r|\n|\r\n]
caracteresTexto = [A-Z|a-z|_|0-9|á|à|â|ã|é|è|ê|í|ï|ó|ô|õ|ö|ú|ç|ñ|Á|À|Â|Ã|É|È|ê|Ê|Í|Ï|Ó|Ô|Õ|Ö|Ú|û|Û|ü|Ü|Ç|Ñ|!|?|º|ª|=|(|)|,|\.|\-|\+|\*|\\|\"|\`|\{|\}|\&|\¨|\§|\¬|\¢|\¹|\²|\³|\£|\´|\~|\°|\r|\n|\r\n|\/|\s|\t|\;|\#|\:|\@|\[|\]|\<|\>|\^|\|]

proximaInstrucao 	= ({terminaLinha}|{palavrasBranco})

comment_body   	 	= {naoFechaChaves}*
naoFechaChaves   	= [^}]
comment_Linha  	 	= {naoTerminaLinha}*
naoTerminaLinha   	= [^\r|\n|\r\n]

comentario_1		= {abreChaves}{comment_body}*{fechaChaves}
comentario_2		= "/*" [^*] ~"*/" | "/*" "*"+ "/"
comentario_3		= [/]{2,2} {comment_Linha}*{terminaLinha}
comentario_4		= "(*" [^*] ~"*)" | "(*" "*"+ ")"
comentario 			= 	(
							{comentario_1}|{comentario_2}|{comentario_3}|{comentario_4}
						)

condicao 			= 	(
							{maior}|{menor}|{maiorIgual}|{menorIgual}|{simboloIgual}
						)

unit 				= {palavraUnit}{proximaInstrucao}*{atributo}{proximaInstrucao}*[\;]

vetor				= 	(
							{identificador}|{atributo}
						)
						(
							{abreColchetes}
							(
								{proximaInstrucao}*|{texto}|{atributo}|{real}|{mais}|{menos}|{multiplica}|{divide}
							)*
							{fechaColchetes}	
						)
						|(
							{abreColchetes}|{fechaColchetes}
						)

mensagem	 		= {palavraShowmessage}

atributo			= 	(
							{identificador}
						)
						( 
							{proximaInstrucao}*[\.]{proximaInstrucao}* {identificador}
						)*

tipoVariavel		= 	(
							{palavraString}|{palavraInteger}|{palavraBoolean}
						)

variavel			= 	{palavraVar}{proximaInstrucao}*{identificador} {palavrasBranco}* [\:] {proximaInstrucao}* 
						(
							{tipoVariavel}|{identificador}
						)
palavraVariavel		= 	{palavraVar}{proximaInstrucao}*{identificador} {proximaInstrucao}* [\:] {proximaInstrucao}* 
						(
							{tipoVariavel}|{identificador}
						)
						{proximaInstrucao}*[\;]

texto				= 	{aspasSimples}{caracteresTexto}*{aspasSimples}

quotedstr			= 	{palavraQuotedstr}{proximaInstrucao}*{abreParenteses}
						(
							{texto}|{variavel}|{proximaInstrucao}
						)*
						{fechaParenteses}


mensagem			= 	{palavraShowmessage}{proximaInstrucao}*{abreParenteses}({texto}|{variavel}|{proximaInstrucao})*{fechaParenteses}{proximaInstrucao}*[\;]

erro				= 	{palavraRaise}{proximaInstrucao}*{palavraException}{proximaInstrucao}*[.]{proximaInstrucao}*{palavraCreate}{abreParenteses}
						(
							{texto}|{variavel}|{proximaInstrucao}|{mais}|{atributo}
						)*
						{fechaParenteses}{proximaInstrucao}*[\;]

SqlClear			= 	{identificador}{palavrasBranco}*[.]{palavrasBranco}*{palavraSQL}{palavrasBranco}*[.]{palavrasBranco}*{palavraClear}{palavrasBranco}*[\;]

SqlAdd				= 	(
							{identificador}{proximaInstrucao}*[.]
						)*
						{proximaInstrucao}*{palavraSQL}{proximaInstrucao}*[.]{proximaInstrucao}*{palavraADD}{proximaInstrucao}*{abreParenteses}
						(
							{proximaInstrucao}|{texto}|{atributo}|{mais}|{quotedstr}|{chamadaMetodo}
						)
						* {fechaParenteses} {proximaInstrucao}*[\;]

SqlExec				= ({identificador}{proximaInstrucao}*[\.])*{proximaInstrucao}*{palavraExecSQL}{proximaInstrucao}*[\;]

SqlOpen				= ({identificador}{proximaInstrucao}*[\.])*{proximaInstrucao}*{palavraOpen}{proximaInstrucao}*[\;]



baseMetodo			= ({palavraProcedure}|{palavraFunction}){proximaInstrucao}*{identificador}{atributo}*[\.]*{proximaInstrucao}*{identificador}*{proximaInstrucao}*	

variaveisMetodo		= ({abreParenteses} ({proximaInstrucao}* {identificador}*{proximaInstrucao}* {identificador}*{proximaInstrucao}*[\:]*{proximaInstrucao}* {variavel}*{proximaInstrucao}*[\;]*[\,]*)* {fechaParenteses})*

metodo 				= {baseMetodo}{variaveisMetodo}{proximaInstrucao}*[\:]*{proximaInstrucao}*{tipoVariavel}*{proximaInstrucao}*[\;]

/*
trim				= {palavraTrim}{proximaInstrucao}*{abreParenteses}{proximaInstrucao}*({texto}|{atributo}){proximaInstrucao}*{fechaParenteses}
*/

paramByName			= {identificador}{proximaInstrucao}*[\.]{proximaInstrucao}*{palavraParambyname}{proximaInstrucao}*{abreParenteses}{proximaInstrucao}*{texto}{proximaInstrucao}*{fechaParenteses}{proximaInstrucao}*[\.]*{proximaInstrucao}*({palavraAsinteger}|{palavraAsstring})*

fieldByName			= {identificador}{proximaInstrucao}*[\.]{proximaInstrucao}*{palavraFieldbyname}{proximaInstrucao}*{abreParenteses}{proximaInstrucao}*{texto}{proximaInstrucao}*{fechaParenteses}{proximaInstrucao}*[\.]*{proximaInstrucao}*({palavraAsinteger}|{palavraAsstring})*


chamadaMetodo		= 	(
							(
								(
								{atributo}{proximaInstrucao}*{abreParenteses}{proximaInstrucao}*
									({texto}|{atributo})
								{proximaInstrucao}*{fechaParenteses}{proximaInstrucao}*
								)[\;]*
								|({atributo}{proximaInstrucao}*[\;])
							)*
						)

atribuicao			= 	(
							(
								{atributo}|{identificador}|{chamadaMetodo}*|{vetor}
							)
							{proximaInstrucao}*{igual}{proximaInstrucao}*
							(
								{atributo}|{identificador}|{texto}|{real}|{chamadaMetodo}*|{vetor}
							)
							{proximaInstrucao}*[\;]
						)

condicaoElseIF			= 	(
								(
									({palavraElse}{proximaInstrucao}*){0,1}
								{palavraIf}){1,1} {proximaInstrucao}* {palavraNot}* {proximaInstrucao}* {abreParenteses}* {proximaInstrucao}*
									({atributo}|{vetor}|{texto}|{identificador}|{real}|{chamadaMetodo})
								{proximaInstrucao}*{condicao} {proximaInstrucao}*
									({atributo}|{vetor}|{texto}|{identificador}|{real}|{chamadaMetodo})
								{proximaInstrucao}*{fechaParenteses}*
					  			{palavraThen}{1,1}
					  		)

program = "program"

%%

{unit}			{ return new PascalToken( "unit", yytext() ); }

{simboloIgual}		{ return new PascalToken( "simboloIgual", yytext() ); }
{mais}				{ return new PascalToken( "soma", yytext() ); }
{menos}				{ return new PascalToken( "subtracao", yytext() ); }
{multiplica}		{ return new PascalToken( "multiplicar", yytext() ); }
{divide}			{ return new PascalToken( "dividir", yytext() ); }

{igual}				{ return new PascalToken( "igual", yytext() ); }
{diferente}			{ return new PascalToken( "diferente", yytext() ); }
{maior}				{ return new PascalToken( "maior", yytext() ); }
{menor}				{ return new PascalToken( "menor", yytext() ); }
{maiorIgual}		{ return new PascalToken( "igual ou maior", yytext() ); }
{menorIgual}		{ return new PascalToken( "igual ou menor", yytext() ); }

{expoente}			{ return new PascalToken( "expoente", yytext() ); }
{arrouba}			{ return new PascalToken( "arrouba", yytext() ); }
{cifrao}			{ return new PascalToken( "cifrao", yytext() ); }
{porcentagem}		{ return new PascalToken( "porcentagem", yytext() ); }

{number}			{ return new PascalToken( "number", yytext() ); }
{real}				{ return new PascalToken( "numero real", yytext() ); }
{integer}			{ return new PascalToken( "integer", yytext() ); }
{aspasSimples}		{ return new PascalToken( "abreImpressao", yytext() ); }
{abreParenteses}	{ return new PascalToken( "abreParenteses", yytext() ); }
{fechaParenteses}	{ return new PascalToken( "fechaParenteses", yytext() ); }

{texto}				{ return new PascalToken( "texto", yytext() ); }
{vetor}				{ return new PascalToken( "vetor", yytext() ); }

{begin}				{ return new PascalToken( "begin", yytext() ); }
{end} 				{ return new PascalToken( "end", yytext() ); }
{palavraInteger}	{ return new PascalToken( "inteiro", yytext() ); }
{palavraString} 	{ return new PascalToken( "string", yytext() ); }
{palavraParserIntToStr}	{ return new PascalToken( "IntToStr", yytext() ); }
{palavraParserStrToInt}	{ return new PascalToken( "StrToInt", yytext() ); }

{metodo}			{ return new PascalToken( "metodo", yytext() ); }
{variaveisMetodo}	{ return new PascalToken( "variaveisMetodo", yytext() ); }
{variavel}			{ return new PascalToken( "declaracaoVariavel", yytext() ); }


{palavraIf}			{ return new PascalToken( "if", yytext() ); }
{palavraThen}		{ return new PascalToken( "then", yytext() ); }

{palavraElse}		{ return new PascalToken( "condicao else", yytext() ); }

{terminaLinha}		{ return createToken("fimLinha", yytext()); }

{comentario}  		{ return createToken("comentario", yytext()); }

{identificador}		{ return createToken("ID", yytext()); }
{atributo}			{ return createToken("atributo", yytext()); }
{delimitadores}		{ return createToken("delimitadores", yytext()); }

{erro}  			{ return createToken("raise error", yytext()); }
{mensagem} 			{ return createToken("showMessage", yytext()); }
{program} 			{ return createToken(yytext(), "");} 
{palavrasBranco} 	{ /**/ }


{SqlClear} 			{ return createToken("SqlClear", yytext()); }
{SqlAdd} 			{ return createToken("SqlAdd", yytext()); }
{SqlExec} 			{ return createToken("SqlExec", yytext()); }
{SqlOpen} 			{ return createToken("SqlOpen", yytext()); }
{paramByName}		{ return createToken("ParamByName", yytext()); }
{fieldByName}		{ return createToken("FieldByName", yytext()); }
{condicaoElseIF}	{ return createToken("condicao else ou if", yytext()); }
{chamadaMetodo}		{ return createToken("chamada Metodo", yytext()); }
{atribuicao}		{ return createToken("atribuicao", yytext()); }

. { throw new RuntimeException("Caractere inválido " + yytext() + " na linha " + yyline + ", coluna " +yycolumn); }
