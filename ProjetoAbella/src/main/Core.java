package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.ArquivoService;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class Core {

	//Todas as palavras que podem ser procuradas
	public static String palavraIf = "(?i:.*IF*)";
	public static String palavraElse = "(?i:.*ELSE*)";
	public static String palavraWhile = "(?i:.*WHILE*)";
	public static String palavraExceptionCreate = "(?i:.*Exception.Create*)";
	public static String palavraProcedure = "(?i:.*PROCEDURE*)";
	public static String palavraFunction = "(?i:.*FUNCTION*)";
	public static String palavraEnd = "(?i:.*END*)";	
	public static String palavraBegin = "(?i:.*BEGIN*)";
	public static String palavraConsulta = "(?i:.*SQL.ADD*)";
	public static String palavraTerminaConsulta = "(?i:.*EXECSQL*)|(?i:.*.OPEN*)";
	public static String palavraDo = "(?i:.*DO*)";
	public static String palavraCometario1Linha = "([//])";	
	public static String palavraCometarioMultiplasLinhaInicio = "([{])";	
	public static String palavraCometarioMultiplasLinhaFim = "([}])";	
	public static String palavraPonto = "([.])";
	public static String palavraAbreParenteses = "([(])";
	public static String palavraFechaParenteses = "([)])";
	public static String palavraPontoVirgula = "([;])";

	//public static String palavraConsulta = "(sql.add|SQL.ADD|Sql.Add|SQL.Add)";
	//public static String palavraExceptionCreate = "(Exception.Create|exception.create)";
	

	private static File arquivoAtual;
	

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
		String espacoAux="";
		arquivoAtual = arquivos;
		numLinha=0;
		String textoArquivoSaida="";
		
		salvaArquivoService(arquivoAtual);
		
		BufferedReader br = null;
	
		try {
			br = new BufferedReader(new FileReader(caminhoArquivo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
		}
		espacadorLinha = 0;
		String espaco="\t";
		
		while(br.ready()){
			String linhaLida = br.readLine().trim();
			numLinha++;
			aux = tipoString(linhaLida.trim(), br) ;
			if(aux.compareTo("")!=0&&!condicaoDeComentario)
			{
				espacoAux="";
				espacoAux =  repeat(espaco, espacadorLinha);
				aux = numLinha+"\t"+espacoAux+aux;
				linhaLida = numLinha+"\t"+espacoAux+linhaLida;
				
				textoArquivoSaida = textoArquivoSaida+linhaLida+System.getProperty("line.separator");
/*
				if(!Configuracoes.isModoSimplificadoImprimir())
					System.out.println(linhaLida);
				else
					System.out.println(aux);
*/
			}
		}			
		br.close();
		
		if(Configuracoes.isHabilitarModoImpressaoArquivo())
		{
			File arquivoSaida = new File(Configuracoes.getDiretorioArquivoSaida(),nomeArquivoSaida);	
			FileWriter arquivoParaEscrever = new FileWriter (arquivoSaida);//arquivo para escrita
			arquivoSaida.createNewFile ();//arquivo criado
			BufferedWriter bufferEscrita= new BufferedWriter (arquivoParaEscrever);
			bufferEscrita.write(textoArquivoSaida);
			bufferEscrita.flush();
			bufferEscrita.close();
			arquivoParaEscrever.close ();
		}
	}
	
	public static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
	
	
	
	/*
	 * Guarda 
	 * */
	private static void salvaArquivoService(File arquivo)
	{
		String[] aux;
		aux = arquivo.getName().split("[.]");
		System.out.println(aux[0]);
		ArquivoService.getArquivo().setNome(aux[0]);
		ArquivoService.salvaArquivo(ArquivoService.getArquivo());
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
		Pattern patternTerminaConsulta = Pattern.compile(palavraTerminaConsulta);
		
		
		
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
	        Matcher matcherTerminaConsulta = patternTerminaConsulta.matcher(palavras[i]);
	        
	        
	        
	        if(matcherConsulta.find())
	        {
	        	String teste="";
	        	String[] splitConsulta;
	        	splitConsulta = str.split("(?i:.*ADD*)|([;])");
	        	for (String string : splitConsulta) {
		        	String[] palavrasDaConsulta = string.split("([(']|[')])");
		        	for (String string2 : palavrasDaConsulta) 
			        	teste+=string2;
				}
	        	if(teste.trim().compareTo("")!=0)
	        		if(ConsultaService.getConsulta().toString().trim()!="")
	        			ConsultaService.getConsulta().setConsulta(ConsultaService.getConsulta()+teste+System.getProperty("line.separator"));
	        	tipo += "consulta";
	        }
	        
	        if(matcherTerminaConsulta.find())
	        {
	        	ConsultaService.getConsulta().setNumLinha(numLinha);
	        	ConsultaService.getConsulta().setDataCadastro(Calendar.getInstance());
	        	ConsultaService.salvaConsulta(ConsultaService.getConsulta());
	        }
	        
	        if(matcherIf.matches())
	        {
	        	String[] splitIf;
	        	splitIf = str.split("IF|If|iF|if|THEN|Then|then");
	        	if(splitIf.length>1)
	        	{
		        	IfService.getIf().setNumLinha(numLinha);
		        	IfService.getIf().setCondicao(splitIf[1]);
		        	IfService.getIf().setDataCadastro(Calendar.getInstance());
		        	IfService.getIf().setMetodoModelo(MetodoService.getListaMetodos().get(MetodoService.getListaMetodos().size()-1));
		        	IfService.salvaIf(IfService.getIf());
		        	
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
			    }
		    	else
		    	{
		    		ExceptionService.getExceptions().setNumLinha(numLinha);
		    		ExceptionService.getExceptions().setMensagem(splitExcepection[1] + ". (Mensagem gerada pelo sistema)");
		    	}
		    	ExceptionService.getExceptions().setCodigoModelo(MetodoService.getListaMetodos().get(MetodoService.getListaMetodos().size()-1));
		    	ExceptionService.getExceptions().setDataCadastro(Calendar.getInstance());
		    	ExceptionService.salvaException(ExceptionService.getExceptions());
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
			    			MetodoService.getMetodo().setArquivoModelo(ArquivoService.getListaArquivos().get(ArquivoService.getListaArquivos().size()-1));
			    			MetodoService.getMetodo().setUnit(metodoProcFuncDivisao[0]);
				    		MetodoService.getMetodo().settipoMetodo(palavras[0]);
				    		MetodoService.getMetodo().setMetodo(metodoProcFuncDivisao[1]);
				    		MetodoService.getMetodo().setNumLinha(numLinha);
				    		MetodoService.getMetodo().setDataCadastro(Calendar.getInstance());
				    		MetodoService.salvaMetodo(MetodoService.getMetodo());
				        	
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
			    			MetodoService.getMetodo().setArquivoModelo(ArquivoService.getListaArquivos().get(ArquivoService.getListaArquivos().size()-1));
			    			MetodoService.getMetodo().setUnit(metodoProcFuncDivisao[0]);
							MetodoService.getMetodo().settipoMetodo(palavras[0]);
							MetodoService.getMetodo().setMetodo(metodoProcFuncDivisao[1]);
							MetodoService.getMetodo().setNumLinha(numLinha);
				    		MetodoService.getMetodo().setDataCadastro(Calendar.getInstance());
							MetodoService.salvaMetodo(MetodoService.getMetodo());
				        	tipo += "Procedure ";
						}
			        }
				}
	        }
		}
		return tipo;
	}
}
