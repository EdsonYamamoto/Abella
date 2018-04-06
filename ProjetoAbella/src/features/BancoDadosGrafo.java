package features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.Configuracoes;
import main.FluxoExtrator;
import modelo.ArquivoModelo;
import modelo.MetodoModelo;
import pacoteGrafo.Edge;
import pacoteGrafo.Node;
import service.ArquivoService;
import service.MetodoService;

public class BancoDadosGrafo {
	
	static String metodo="Metodo";
	static String arquivo = "Arquivo";
	static String pasName = "PasName";
	static String unit = "Unit";
	static String relacao = "Possui";
	static String fluxo = "Fluxo";

	static File arquivoSaida;	
	static FileWriter arquivoParaEscrever;
	static BufferedWriter bufferEscrita;

	static String linhaEscritaArquivo="";
	

	static String nomeArquivoSaidaScriptNos = "scriptCreateNos.txt";//o nome do arquivo com a extensão
	static String nomeArquivoSaidaScriptUnitMetodo = "scriptCreateArestas.txt";//o nome do arquivo com a extensão
	static String nomeArquivoSaidaScriptDeleteTudo = "scriptDeletaTudo.txt";//deletar os nos e as relações
	static String nomeArquivoSaidaScriptMetodoMetodo = "scriptCreateArestasEntreMetodos.txt";//deletar os nos e as relações
	static String dirPath = Configuracoes.getDiretorioArquivoSaida()+"/scriptsNeo4J";
	
	public static void impressaoDadosBancoNeo4j() throws IOException
	{
		File diretorioSaida = new File(dirPath);
		if(!diretorioSaida.exists())
		{
			diretorioSaida.mkdirs();
			System.out.println("Diretorio "+ diretorioSaida.getPath() +" Criado");
		}
		
		escreveMetodoDeletar();
		escreveNos();
		escreveNosUnitArestaMetodo();
		escreveMetodoArestaMetodo();

	}

	private static void escreveMetodoArestaMetodo() throws IOException 
	{
		/*
		 * Criacao das arestas
		 * */
		linhaEscritaArquivo="";
		arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptMetodoMetodo);	
		arquivoParaEscrever = new FileWriter (arquivoSaida);
		bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		bufferEscrita.newLine ();
		bufferEscrita.flush();

		for (Node n : FluxoExtrator.arvore.nodes) {
			for (Edge e : n.getEdges()) {
				linhaEscritaArquivo= "match ("+e.from.nome+":"+metodo+") where "+e.from.nome+"."+pasName+"=\""+n.pasName+"\""+System.lineSeparator();
				linhaEscritaArquivo+= "match ("+e.to.nome+":"+metodo+") where "+e.to.nome+"."+pasName+"=\""+n.pasName+"\""+System.lineSeparator();
				linhaEscritaArquivo+="Create"+System.lineSeparator();
				linhaEscritaArquivo+="("+e.from.nome+")-[:"+fluxo+"]->("+e.to.nome+")"+System.lineSeparator()+System.lineSeparator();
				bufferEscrita.write (linhaEscritaArquivo);
				bufferEscrita.newLine ();
				bufferEscrita.flush();
			}
		}
		arquivoParaEscrever.close ();
	}
	private static void escreveMetodoDeletar() throws IOException 
	{
		/*
		 * Metodo de deletar
		 * */
		linhaEscritaArquivo="";
		arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptDeleteTudo);	
		arquivoParaEscrever = new FileWriter (arquivoSaida);
		bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		bufferEscrita.newLine ();
		bufferEscrita.flush();
		
		
		linhaEscritaArquivo= "MATCH (n)"+System.lineSeparator();
				
		linhaEscritaArquivo+="DETACH DELETE n"+System.lineSeparator();
		bufferEscrita.write (linhaEscritaArquivo);//Leia um arquivo e Escreva no outro
		bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
		bufferEscrita.flush();
		arquivoParaEscrever.close ();
	}
	private static void escreveNos() throws IOException 
	{
		/*
		 * Criacao dos nos
		 * */
		arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptNos);	
		arquivoParaEscrever = new FileWriter (arquivoSaida);
		bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		bufferEscrita.write ("CREATE");
		bufferEscrita.newLine ();
		bufferEscrita.flush();
		for (ArquivoModelo c : ArquivoService.getListaArquivos()) 
		{
			linhaEscritaArquivo = "("+c.getNome()+":"+unit+"{"+unit+":\""+c.getNome()+"\"}),";
			
			bufferEscrita.write (linhaEscritaArquivo);//Leia um arquivo e Escreva no outro
			bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
			bufferEscrita.flush();
		}
		
		for (MetodoModelo c : MetodoService.getListaMetodos()) 
		{
			linhaEscritaArquivo = "("+c.getUnit()+c.getMetodo()+":"+metodo+"{"+metodo+":\""+c.getMetodo()+"\","+pasName+":\""+c.getArquivoModelo().getNome()+"\"})";
			if (MetodoService.getListaMetodos().indexOf(c)!=MetodoService.getListaMetodos().size()-1) 
				linhaEscritaArquivo = linhaEscritaArquivo+",";
			
			bufferEscrita.write (linhaEscritaArquivo);//Leia um arquivo e Escreva no outro
			bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
			bufferEscrita.flush();
		}
		arquivoParaEscrever.close ();
	}
	private static void escreveNosUnitArestaMetodo() throws IOException 
	{
		/*
		 * Criacao das arestas
		 * */
		linhaEscritaArquivo="";
		arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptUnitMetodo);	
		arquivoParaEscrever = new FileWriter (arquivoSaida);
		bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		bufferEscrita.newLine ();
		bufferEscrita.flush();
		
		
		ArrayList<ArquivoModelo> listaArquivos = new ArrayList<ArquivoModelo>();
		for (MetodoModelo c : MetodoService.getListaMetodos()) 
		{
			if(!listaArquivos.contains(c.getArquivoModelo()))
			{
				linhaEscritaArquivo="";
				linhaEscritaArquivo= "match ("+c.getUnit()+c.getMetodo()+":"+metodo+") where "+c.getUnit()+c.getMetodo()+"."+pasName+"=\""+c.getArquivoModelo().getNome()+"\""+System.lineSeparator();
				
				linhaEscritaArquivo+="match ("+c.getArquivoModelo().getNome()+":"+unit+") where "+c.getArquivoModelo().getNome()+"."+unit+"=\""+c.getArquivoModelo().getNome()+"\""+System.lineSeparator();
				
				linhaEscritaArquivo+="Create"+System.lineSeparator();
				linhaEscritaArquivo+="("+c.getUnit()+c.getMetodo()+")-[:"+relacao+"]->("+c.getArquivoModelo().getNome()+")"+System.lineSeparator()+System.lineSeparator();
				
				bufferEscrita.write (linhaEscritaArquivo);//Leia um arquivo e Escreva no outro
				bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
				bufferEscrita.flush();
				listaArquivos.add(c.getArquivoModelo());
			}
		}
		arquivoParaEscrever.close ();
	}
}
