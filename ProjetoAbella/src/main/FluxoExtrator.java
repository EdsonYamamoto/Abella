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

import modelo.ArquivoModelo;
import modelo.MetodoModelo;
import pacoteGrafo.*;
import service.ArquivoService;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class FluxoExtrator {
	//Todas as palavras que podem ser procuradas
		public static String palavraIf = "(?i:.*IF*)";
		public static String palavraElse = "(?i:.*ELSE*)";
		public static String palavraWhile = "(?i:.*WHILE*)";
		public static String palavraExceptionCreate = "(?i:.*EXCEPTION.CREATE*)";
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
		public static Graph arvore;
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
		public static void escreveFluxoPrograma(File arquivos) throws IOException {
			arvore = new Graph();
			String nomeArquivoSaida;	//o nome do arquivo com a extensão
			String caminhoArquivo = arquivos.getAbsolutePath();
			String aux="";
			String testeTextoGrafo="";
			
			BufferedReader br = null;

			try {
				br = new BufferedReader(new FileReader(caminhoArquivo));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block			
				e.printStackTrace();
			}
			while(br.ready()){
				String linhaLida = br.readLine().trim();
				fluxoPrograma(linhaLida.trim(), br) ;
				//if(aux.compareTo("")!=0&&!condicaoDeComentario)
				//{
				//	textoArquivoSaida = textoArquivoSaida+linhaLida+System.getProperty("line.separator");

				//}
			}			
			br.close();
			/*
			for (Node n : arvore.nodes) {
				for (Edge e : n.getEdges()) {
					System.out.println(e.from+" -> "+e.to);
				}
			}
			*/
			
			if(Configuracoes.isHabilitarModoImpressaoFluxoPrograma())
			{
				File diretorioSaida = new File(Configuracoes.getDiretorioArquivoSaida()+"/ModoFluxoImpresso");
				if(!diretorioSaida.exists())
				{
					diretorioSaida.mkdirs();
					System.out.println("Diretorio "+ diretorioSaida.getPath() +" Criado");
				}
				
				testeTextoGrafo="";
				
				File arquivoSaida = new File(Configuracoes.getDiretorioArquivoSaida()+"/ModoFluxoImpresso",arquivos.getName());
				FileWriter arquivoParaEscrever = new FileWriter (arquivoSaida);//arquivo para escrita
				arquivoSaida.createNewFile ();//arquivo criado
				BufferedWriter bufferEscrita= new BufferedWriter (arquivoParaEscrever);
				bufferEscrita.write(testeTextoGrafo);
				bufferEscrita.flush();
				bufferEscrita.close();
				arquivoParaEscrever.close ();
			}
		}
		
		private static void fluxoPrograma(String str, BufferedReader br)
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
		        
		        
		        /*
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
		         * 
		         */
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
				    			for (MetodoModelo metodo : MetodoService.getListaMetodos()) 
									if (metodoProcFuncDivisao[0].compareTo(metodo.getUnit())==0 && metodoProcFuncDivisao[1].compareTo(metodo.getMetodo())==0) 
									{
										MetodoService.setMetodo(metodo);
										arvore.addNode(MetodoService.getMetodo().getMetodo(),MetodoService.getMetodo().getUnit());
									}
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
				    			for (MetodoModelo metodo : MetodoService.getListaMetodos()) 
									if (metodoProcFuncDivisao[0].compareTo(metodo.getUnit())==0 && metodoProcFuncDivisao[1].compareTo(metodo.getMetodo())==0) 
									{
										MetodoService.setMetodo(metodo);
										arvore.addNode(MetodoService.getMetodo().getMetodo(), MetodoService.getMetodo().getUnit());
									}
					        	tipo += "Procedure ";
							}
				        }
					}
		        }
		        
		        for (MetodoModelo metodo : MetodoService.getListaMetodos()) {
					if(metodo.getMetodo().compareTo(palavras[i])==0)
					{
						arvore.addNode(palavras[i], MetodoService.getMetodo().getUnit());
						arvore.addEdge(MetodoService.getMetodo().getMetodo(), palavras[i], "oi?");
					}
				}
				
			}
		}
}
