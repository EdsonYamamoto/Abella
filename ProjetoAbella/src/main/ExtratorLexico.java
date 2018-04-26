package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;

import javax.swing.GrayFilter;

import compiladorPascal.LexicalAnalyzer;
import compiladorPascal.PascalToken;
import pacoteGrafo.Graph;
import pacoteGrafo.Node;
import service.ArquivoService;
import service.ConsultaService;
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
	
	public static boolean tokenAddSql=true;
	public static boolean tokenOpenSql=false;
	public static boolean tokenExecSql=false;
	public static boolean tokenSqlClear=false;
	
	public static void extrator(File sourceCode)
	{
		Graph grafo = new Graph();
		Node no = new Node();
		Node noAnterior = new Node();
		String frase = new String();
	    PascalToken token;
	    try {
		LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
		
			salvaArquivoService(sourceCode);
			
			while ((token = lexical.yylex()) != null) {
				token.value = token.value.replaceAll("([\r]|[\n]|[\r\n]|[\t])","");
				//System.out.println(token.name.trim());

				frase = "";
				if(token.name.trim().compareTo("unit")==0 && tokenUnit)
				{
					System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
				}
				
				if(token.name.trim().compareTo("metodo")==0 && tokenMetodo)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
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
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					frase = separaAddSQL(token.value);
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
				grafo.addNode(frase);
				
				if(grafo.nodes.size()>1)
				{
					grafo.addEdge(no.nome, noAnterior.nome,"");
				}
				noAnterior = grafo.nodes.get(grafo.nodes.size()-1);
				no = grafo.nodes.get(grafo.nodes.size()-1);
				
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
	
	private static String separaAddSQL(String frase)
	{
		String teste="";
		String[] splitConsulta;
		
		splitConsulta = frase.split("([Aa][Dd][Dd])");
		if(splitConsulta.length>1)
		{
			teste = splitConsulta[1].trim().substring(0, splitConsulta[1].length()-1);
			System.out.println(frase+"\n"+teste+"\n\n");
		}
		return teste;
	}
}
