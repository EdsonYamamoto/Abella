package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;

import javax.swing.GrayFilter;

import compiladorPascal.LexicalAnalyzer;
import compiladorPascal.PascalToken;
import pacoteGrafo.Edge;
import pacoteGrafo.Graph;
import pacoteGrafo.Node;
import service.ArquivoService;
import service.ConsultaService;
import service.MetodoService;

public class ExtratorLexico {
	
	public static boolean tokenUnit=true;
	public static boolean tokenMetodo=true;
	public static boolean tokenBeginEnd=false;

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
	
	public static Graph grafo;
	public static int i=0;
			
	public static void extrator(File sourceCode)
	{
		grafo = new Graph();
		Node no;
		Node noUnit = null;
		Node noMetodo = null;
	    PascalToken token;
	    try {
		LexicalAnalyzer lexical = new LexicalAnalyzer(new FileReader(sourceCode));
		
			salvaArquivoService(sourceCode);
			
			while ((token = lexical.yylex()) != null) {
				token.value = token.value.replaceAll("([\r]|[\n]|[\r\n]|[\t])","");
				//System.out.println(token.name.trim());
				no = new Node();

				if(token.name.trim().compareTo("unit")==0 && tokenUnit)
				{
					no = guardaDadosNode(token.name, token.value);
					
					noUnit = new Node(no.id,no.nome,no.informacao);
					grafo.addNode(no.id,no.nome,no.informacao);
				}
				if(token.name.trim().compareTo("metodo")==0 && tokenMetodo)
				{
					no = guardaDadosNode(token.name, token.value);
					
					noMetodo = new Node(no.id,no.nome,no.informacao);

					grafo.addNode(no.id,no.nome,no.informacao);
					grafo.addEdge(noUnit.id,no.id,"");
				}
				
				if(token.name.trim().compareTo("condicao else ou if")==0  && tokenElseIf)
				{
					no = guardaDadosNode(token.name, token.value);
					
					grafo.addNode(no.id,no.nome,no.informacao);
					grafo.addEdge(noMetodo.id,no.id,"");
				}
				
				if(token.name.trim().compareTo("condicao else")==0  && tokenElse)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("atribuicao")==0  && tokenAtribuicao)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("SqlAdd")==0  && tokenAddSql)
				{
					no = guardaDadosNode(token.name, token.value);
				}

				if(token.name.trim().compareTo("SqlClear")==0  && tokenSqlClear)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}

				if(token.name.trim().compareTo("SqlOpen")==0  && tokenOpenSql)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("SqlExec")==0  && tokenExecSql)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("raise error")==0  && tokenRaiseError)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("except")==0  && tokenExcept)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("declaracaoVariavel")==0  && tokenDeclaracaoVariavel)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("comentario")==0  && tokenComentario)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
				if(token.name.trim().compareTo("showMessage")==0  && tokenMessagem)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				if((token.name.trim().compareTo("begin")==0||
					token.name.trim().compareTo("end")==0||
					token.name.trim().compareTo("end If")==0 ) && tokenBeginEnd)
				{
					//System.out.println(sourceCode.getName()+" lin:"+token.line+" -"+token.name + "\t- " + token.value);
					
					no = guardaDadosNode(token.name, token.value);
				}
				
/*
				if(no.nome!=null)
				{
					grafo.addNode(i,no.nome,no.informacao);
					System.out.println(grafo.nodes.get(grafo.nodes.size()-1));
					grafo.addEdge(i-1, i, "");
				}
	*/			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (Node n : grafo.nodes) {
			for (Edge e : n.getArestas()) {
				System.out.println(e.from.informacao+"-->  "+e.to.informacao);
			}
		}
	}
	
	private static Node guardaDadosNode(String nome, String info)
	{
		i++;
		Node no = new Node();
		no.id = i;
		no.nome = nome;
		no.informacao = info;
		return no;
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

			teste = splitConsulta[1].trim().substring(0, splitConsulta[1].length()-1);
			System.out.println(frase+"\n"+teste+"\n\n");
		return teste;
	}
}
