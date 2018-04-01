package features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import main.Configuracoes;
import modelo.ArquivoModelo;
import modelo.MetodoModelo;
import service.ArquivoService;
import service.MetodoService;

public class BancoDadosGrafo {
	
	static String metodo="Metodo";
	static String arquivo = "Arquivo";
	static String pasName = "PasName";
	static String unit = "Unit";
	static String relacao = "Possui";
	
	public static void impressaoDadosBancoNeo4j() throws IOException
	{
		
		String nomeArquivoSaidaScriptNos = "scriptCreateNos.txt";//o nome do arquivo com a extensão
		String nomeArquivoSaidaScriptArestas = "scriptCreateArestas.txt";//o nome do arquivo com a extensão
		String dirPath = Configuracoes.getDiretorioArquivoSaida()+"/scriptsNeo4J";
		File diretorioSaida = new File(dirPath);
		if(!diretorioSaida.exists())
		{
			diretorioSaida.mkdirs();
			System.out.println("Diretorio "+ diretorioSaida.getPath() +" Criado");
		}
		
		File arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptNos);	
		FileWriter arquivoParaEscrever = new FileWriter (arquivoSaida);
		BufferedWriter bufferEscrita= new BufferedWriter (arquivoParaEscrever);

		String linhaEscritaArquivo="";
		
		
		
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
		
		
		

		/*
		 * Criacao das arestas
		 * */
		linhaEscritaArquivo="";
		arquivoSaida = new File(dirPath,nomeArquivoSaidaScriptArestas);	
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
