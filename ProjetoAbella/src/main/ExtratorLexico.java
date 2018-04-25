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

	public static boolean tokenComentario=false;
	
	public static boolean tokenMessagem=false;
	public static boolean tokenRaiseError=false;
	public static boolean tokenExcept=false;
	
	public static boolean tokenElseIf=false;
	public static boolean tokenElse=false;
	
	public static boolean tokenDeclaracaoVariavel=false;
	public static boolean tokenAtribuicao=false;
	
	public static boolean tokenAddSql=false;
	public static boolean tokenOpenSql=false;
	public static boolean tokenExecSql=false;
	public static boolean tokenSqlClear=false;
	
	public static void extrator(File sourceCode)
	{
	    PascalToken token;
	    try {
		LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
		
			salvaArquivoService(sourceCode);
			
			while ((token = lexical.yylex()) != null) {
				token.value = token.value.replaceAll("([\r]|[\n]|[\r\n]|[\t])","");
				//System.out.println(token.name.trim());
				
				if(token.name.trim().compareTo("unit")==0 && tokenUnit)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("metodo")==0 && tokenMetodo)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					MetodoService.getMetodo().setArquivoModelo(ArquivoService.getListaArquivos().get(ArquivoService.getListaArquivos().size()-1));
				}
				
				if(token.name.trim().compareTo("condicao else ou if")==0  && tokenElseIf)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("condicao else")==0  && tokenElse)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("atribuicao")==0  && tokenAtribuicao)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("SqlAdd")==0  && tokenAddSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}

				if(token.name.trim().compareTo("SqlClear")==0  && tokenSqlClear)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}

				if(token.name.trim().compareTo("SqlOpen")==0  && tokenOpenSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("SqlExec")==0  && tokenExecSql)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("raise error")==0  && tokenRaiseError)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("except")==0  && tokenExcept)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				
				if(token.name.trim().compareTo("declaracaoVariavel")==0  && tokenDeclaracaoVariavel)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("comentario")==0  && tokenComentario)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("showMessage")==0  && tokenMessagem)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				
				if(
						token.name.trim().compareTo("fimLinha")!=0
						/*
						&&token.name.trim().compareTo("metodo")!=0&&
						token.name.trim().compareTo("condicao else ou if")!=0&&
						token.name.trim().compareTo("condicao else")!=0&&
						token.name.trim().compareTo("atribuicao")!=0&&
						token.name.trim().compareTo("SqlAdd")!=0&&
						token.name.trim().compareTo("SqlOpen")!=0&&
						token.name.trim().compareTo("SqlExec")!=0&&
						token.name.trim().compareTo("declaracaoVariavel")!=0&&
						token.name.trim().compareTo("raise error")!=0&&
						token.name.trim().compareTo("comentario")!=0&&
						token.name.trim().compareTo("end")!=0&&
						token.name.trim().compareTo("begin")!=0&&
						token.name.trim().compareTo("showMessage")!=0&&
						token.name.trim().compareTo("except")!=0&&
						token.name.trim().compareTo("SqlClear")!=0
						*/
					)
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
				//if(token.name!="fimLinha" )
				//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				
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
