package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;

import compiladorPascal.LexicalAnalyzer;
import compiladorPascal.PascalToken;
import service.ArquivoService;
import service.MetodoService;

public class ExtratorLexico {
	public static boolean tokenUnit=false;
	public static boolean tokenMetodo=false;
	public static boolean tokenElseIf=false;
	public static boolean tokenElse=false;
	public static boolean tokenAtribuicao=false;
	public static boolean tokenAddSql=true;
	public static boolean tokenOpenSql=true;
	public static boolean tokenExecSql=true;
	public static boolean tokenRaiseError=false;
	
	public static void extrator(File sourceCode)
	{
	    PascalToken token;
	    try {
		LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
		
			salvaArquivoService(sourceCode);
			
			while ((token = lexical.yylex()) != null) {
				token.value = token.value.replaceAll("([\r]|[\n]|[\r\n]|[\t])","");
				
				if(token.name=="unit" && tokenUnit)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
				
				if(token.name=="metodo" && tokenMetodo)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
					MetodoService.getMetodo().setArquivoModelo(ArquivoService.getListaArquivos().get(ArquivoService.getListaArquivos().size()-1));
				}
				if(token.name=="condicao else ou if" && tokenElseIf)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
				if(token.name=="condicao else" && tokenElse)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
				if(token.name=="atribuicao" && tokenAtribuicao)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
				if(token.name=="SqlAdd" && tokenAddSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}

				if(token.name=="SqlOpen" && tokenOpenSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}

				if(token.name=="SqlExec" && tokenExecSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
				
				if(token.name=="raise error" && tokenRaiseError)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				}
					
				//if(token.name!="fimLinha" )
				//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "-\t\t " + token.value);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void salvaArquivoService(File arquivo)
	{
		String[] aux;
		aux = arquivo.getName().split("[.]");
		ArquivoService.getArquivo().setNome(aux[0]);
		ArquivoService.getArquivo().setDataCadastro(Calendar.getInstance());
		ArquivoService.salvaArquivo(ArquivoService.getArquivo());
	}
}
