package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.ConsultaModelo;
import modelo.ExceptionsModelo;
import modelo.IfModelo;
import modelo.MetodoModelo;
import modelo.Procura;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class Core {

	//Todas as palavras que podem ser procuradas
	public static String palavraIf = "(IF|if|If|iF)";
	public static String palavraElse = "(ELSE|Else|ELse|ELSe|else)";
	public static String palavraWhile = "(While|while|WHILE)";
	public static String palavraDo = "(DO|do)";
	public static String palavraCometario1Linha = "([//])";	
	public static String palavraCometarioMultiplasLinhaInicio = "([{])";	
	public static String palavraCometarioMultiplasLinhaFim = "([}])";	
	public static String palavraExceptionCreate = "(Exception.Create|exception.create)";
	public static String palavraProcedure = "(procedure|Procedure|PROCEDURE)";
	public static String palavraFunction = "(function|Function|FUNCTION)";
	public static String palavraPonto = "([.])";
	public static String palavraAbreParenteses = "([(])";
	public static String palavraFechaParenteses = "([)])";
	public static String palavraPontoVirgula = "([;])";
	public static String palavraEnd = "(end|END|End)";	
	public static String palavraBegin = "(Begin|begin|BEGIN)";
	public static String palavraConsulta = "(sql.add|SQL.ADD|Sql.Add|SQL.Add)";
	
	public static String palavraProcura = "(IdTipoServico)";

	public static List<Procura> listaProcura = new ArrayList<Procura>();
	public static Procura procura = new Procura();
	

	/*
	 * Atributos para funcionar o codigo
	 * */
	static int numLinha=0;
	static public boolean condicaoDeComentario = false;
	static public int espacadorLinha = 0;
	/*
	 * */
	//codigo q reescreve todas o arquivo de texto
	public static void reescreveCodigo(File arquivos) throws IOException {
		String nomeArquivo = arquivos.getName();
		String nomeArquivoSaida = nomeArquivo+Configuracoes.getExtensaoArquivoSaida();	//o nome do arquivo com a extensão
		String caminhoArquivo = arquivos.getAbsolutePath();
		String aux;
		numLinha=0;
		String espacoAux="";
		
		File arquivoSaida = new File(nomeArquivoSaida);	
	      /*ESCREVER*/
		FileWriter arquivoParaEscrever = new FileWriter (arquivoSaida);//arquivo para escrita
	
		BufferedReader br = null;
	
		try {
			br = new BufferedReader(new FileReader(caminhoArquivo));
			arquivoSaida.createNewFile ();//arquivo criado
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}
		espacadorLinha = 0;
		BufferedWriter bufferEscrita= new BufferedWriter (arquivoParaEscrever);
		String espaco="\t";
		
		while(br.ready()){
			String escritaArquivo = br.readLine().trim();
			numLinha++;

			MetodoService.getMetodo().setPasName(arquivos.getName());
			aux = tipoString(escritaArquivo.trim(), br) ;
			if(aux.compareTo("")!=0&&!condicaoDeComentario&&Configuracoes.isModoImprimir())
			{
				espacoAux="";
				espacoAux =  repeat(espaco, espacadorLinha);
				//System.out.println(espacadorLinha);
				aux = numLinha+"\t"+espacoAux+aux;
				
				escritaArquivo = numLinha+"\t"+espacoAux+escritaArquivo;
				if(!Configuracoes.isModoSimplificadoImprimir())
					System.out.println(escritaArquivo);
				else
					System.out.println(aux);
				
				if(Configuracoes.isModoImprimir())
				{
					bufferEscrita.write (escritaArquivo);//Leia um arquivo e Escreva no outro
					bufferEscrita.newLine ();//pula uma linha no arquivoescrever (result);
				}
				
			}
			
		}			
		br.close();
		arquivoParaEscrever.close ();
	}
	
	public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
	
	
	
	
	/*
	 * Encontra palavras reservadas
	 * */
	private static String tipoString(String str, BufferedReader br)
	{
		
		String[] palavras, metodoProcFuncDivisao;
		String tipo = "";
		
		palavras = str.split(" ");
		
		Pattern patternIf = Pattern.compile(palavraIf);
		Pattern patternElse = Pattern.compile(palavraElse);
		Pattern patternWhile = Pattern.compile(palavraWhile);
		Pattern patternDo = Pattern.compile(palavraDo);
		Pattern patternExceptionCreate = Pattern.compile(palavraExceptionCreate);
		Pattern patternComentario1Linha = Pattern.compile(palavraCometario1Linha);
		Pattern patternComentarioMultiplasLinhaInicio = Pattern.compile(palavraCometarioMultiplasLinhaInicio);
		Pattern patternComentarioMultiplasLinhaFim = Pattern.compile(palavraCometarioMultiplasLinhaFim);
		Pattern patternProcedure = Pattern.compile(palavraProcedure);
		Pattern patternFunction = Pattern.compile(palavraFunction);
		Pattern patternPonto = Pattern.compile(palavraPonto);
		Pattern patternPontoVirugula = Pattern.compile(palavraPontoVirgula);
		Pattern patternEnd = Pattern.compile(palavraEnd);
		Pattern patternBegin = Pattern.compile(palavraBegin);
		Pattern patternConsulta = Pattern.compile(palavraConsulta);
		Pattern patternProcura = Pattern.compile(palavraProcura);
		
		
		for(int i=0;i<palavras.length;i++)	
		{
	        Matcher matcherIf = patternIf.matcher(palavras[i]);
	        Matcher matcherElse = patternElse.matcher(palavras[i]);
	        Matcher matcherWhile = patternWhile.matcher(palavras[i]);
	        Matcher matcherDo = patternDo.matcher(palavras[i]);
	        Matcher matcherExceptionCreate = patternExceptionCreate.matcher(palavras[i]);
	        Matcher matcherComentario1Linha = patternComentario1Linha.matcher(palavras[i]);
	        Matcher matcherComentarioMultiplasLinhaInicio = patternComentarioMultiplasLinhaInicio.matcher(palavras[i]);
	        Matcher matcherComentarioMultiplasLinhaFim = patternComentarioMultiplasLinhaFim.matcher(palavras[i]);
	        Matcher matcherFunction = patternFunction.matcher(palavras[i]);
	        Matcher matcherProcedure = patternProcedure.matcher(palavras[i]);
	        Matcher matcherPontoVirgula = patternPontoVirugula.matcher(palavras[i]);
	        Matcher matcherEnd = patternEnd.matcher(palavras[i]);
	        Matcher matcherBegin = patternBegin.matcher(palavras[i]);
	        Matcher matcherConsulta = patternConsulta.matcher(palavras[i]);
	        Matcher matcherProcura = patternProcura.matcher(palavras[i]);
	        
	        
	        if(matcherProcura.find())
	        {
	        	procura.setNumLinha(numLinha);
	        	procura.setMensagem(str);
	        	listaProcura.add(procura);
	        	procura= new Procura();
	        }
	        
	        if(matcherConsulta.find())
	        {
	        	String[] splitConsulta;
	        	splitConsulta = str.split("add|Add|ADD|;");
	        	ConsultaService.getConsulta().setNumLinha(numLinha);
	        	ConsultaService.getConsulta().setConsulta(splitConsulta[1]);
	        	
	        	
	        	ConsultaService.salvaConsulta(ConsultaService.getConsulta());
	        	ConsultaService.setConsulta(new ConsultaModelo());
	        	
	        	tipo += "consulta";
	        }
	        
	        if(matcherIf.matches())
	        {
	        	String[] splitIf;
	        	splitIf = str.split("(if|IF|If|then|Then|THEN)");
	        	if(splitIf.length>1)
	        	{
		        	IfService.getIf().setNumLinha(numLinha);
		        	IfService.getIf().setCondicao(splitIf[1]);
		        	IfService.salvaIf(IfService.getIf());
		        	IfService.setIf(new IfModelo());
		        	
		        	//for (String s : splitIf) 
		        	//	System.out.println(numLinha+"\t"+s);

	        	}
		        tipo += "If ";
	        }
	        if(matcherElse.matches())
	        	tipo += "Else ";
	        
	        if(matcherWhile.matches())
	        	tipo += "While ";
	        if(matcherDo.matches())
	        	tipo += "Do ";
	        	
	        if(matcherExceptionCreate.find())
	        {
	        	String[] splitExcepection;
		    	splitExcepection = str.split("[(']|[')]");
		    	if(splitExcepection.length>2)
		    	{
			    	ExceptionService.getExceptions().setNumLinha(numLinha);
			    	ExceptionService.getExceptions().setMensagem(splitExcepection[2]);
			    	ExceptionService.getExceptions().setCodigoModelo(MetodoService.getListaMetodos().get(MetodoService.getListaMetodos().size()-1));
			    	
			    	
			    }
		    	else
		    	{
		    		ExceptionService.getExceptions().setNumLinha(numLinha);
		    		ExceptionService.getExceptions().setMensagem(splitExcepection[1] + ". (Mensagem gerada pelo sistema)");
		    		ExceptionService.getExceptions().setCodigoModelo(MetodoService.getListaMetodos().get(MetodoService.getListaMetodos().size()-1));
		    		
		    	}
		    	
		    	
		    	ExceptionService.salvaException(ExceptionService.getExceptions());
	    		ExceptionService.setExceptions(new ExceptionsModelo());
	        	tipo += "ExceptionCreate ";
	        }

	        if(matcherComentario1Linha.find())
	        {
	        	//condicaoDeComentario = true;
	        	//tipo += "ComentarioDeLinha ";
	        }
	        
	        
	        if(matcherComentarioMultiplasLinhaInicio.find())
	        {
	        	condicaoDeComentario=true;
	        	tipo += "ComentarioVariasLinhas Inicia ";
	        }

	        if(matcherComentarioMultiplasLinhaFim.find())
	        {
	        	condicaoDeComentario=false;
	        	tipo += "ComentarioVariasLinhas FIM ";
	        }
	        
	        if(matcherPontoVirgula.find())
	        {
	        	condicaoDeComentario=false;
	        	//tipo += "Fim de comando FIM ";
	        }
	        
	        //
	        // Espacadores
	        //
	        if(matcherBegin.find())
	        	espacadorLinha++;
	        if(matcherEnd.find())
	        	if(espacadorLinha>0)
	        	espacadorLinha--;

	        //
	        // Matcher encontra metodos
	        // 
	        if(matcherFunction.matches())
	        {
	        	condicaoDeComentario=true;
	        	for(String p : palavras)
	        	{
			        Matcher matcherPonto = patternPonto.matcher(p);
			        if(matcherPonto.find())
			        {
			    		metodoProcFuncDivisao = p.split("[.]|[(]|[)]|[;]|[:]");
			    		if(metodoProcFuncDivisao.length!=1)
			    		{
				    		MetodoService.getMetodo().setUnit(metodoProcFuncDivisao[0]);
				    		MetodoService.getMetodo().settipoMetodo(palavras[0]);
				    		MetodoService.getMetodo().setMetodo(metodoProcFuncDivisao[1]);
				    		MetodoService.getMetodo().setNumLinha(numLinha);
				        	MetodoService.salvaMetodo(MetodoService.getMetodo());
							MetodoService.setMetodo(new MetodoModelo());
				        	tipo += "Função ";
			    		}
			        }
	        	}
	        }
	        if(matcherProcedure.matches())
	        {
	        	condicaoDeComentario=true;
	        	for (String p : palavras) {
			        Matcher matcherPonto = patternPonto.matcher(p);
			        if(matcherPonto.find())
			        {
			    		metodoProcFuncDivisao = p.split("[.]|[(]|[)]|[;]");
						if(metodoProcFuncDivisao.length!=1)
						{
							MetodoService.getMetodo().setUnit(metodoProcFuncDivisao[0]);
							MetodoService.getMetodo().settipoMetodo(palavras[0]);
							MetodoService.getMetodo().setMetodo(metodoProcFuncDivisao[1]);
							MetodoService.getMetodo().setNumLinha(numLinha);
							MetodoService.salvaMetodo(MetodoService.getMetodo());
							MetodoService.setMetodo(new MetodoModelo());
				        	tipo += "Procedure ";
						}
			        }
				}
	        }
		}
		
		return tipo;
	}
}
