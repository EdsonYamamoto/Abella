package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TesteExtracao {
	private static String nomeArquivoSaida; 
	public static void extracaoteste(File arquivo){
		String caminhoArquivo = arquivo.getAbsolutePath();
		String textoArquivoSaida = "";
		String novoTextoArquivoSaida = "";
		
		String separadorPalavras = "[^a-zA-Z0-9]";
		
		String[] nome = arquivo.getName().split("[.]");
		if(nome.length!=0)
			nomeArquivoSaida = nome[0];
		else
			nomeArquivoSaida = "fazSentido";
		
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(caminhoArquivo));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (br.ready()) {
				String linhaLida = br.readLine().trim();
				if (linhaLida.length() != 0)
					textoArquivoSaida += linhaLida + " ";
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Pattern pat = Pattern.compile(separadorPalavras);
		Matcher mat = pat.matcher(textoArquivoSaida);
		while(mat.find()) {
			
			//System.out.println(mat.group(0));	

			//System.out.println(mat.group(0));
			novoTextoArquivoSaida+=mat.replaceAll(" "+mat.group(0) +" ");
		}
		
		System.out.println(novoTextoArquivoSaida);
		
		

	}
	public static void imprimir(String textoArquivoSaida) throws IOException
	{
		StringTokenizer st = new StringTokenizer(textoArquivoSaida, " ");
		
		File diretorioSaida = new File(Configuracoes.getDiretorioArquivoSaida() + "/ModoTesteImpresso");
		if (!diretorioSaida.exists()) {
			diretorioSaida.mkdirs();
			System.out.println("Diretorio " + diretorioSaida.getPath() + " Criado");
		}
		File arquivoSaida = new File(Configuracoes.getDiretorioArquivoSaida() + "/ModoTesteImpresso"+ nomeArquivoSaida+".pas");
		FileWriter arquivoParaEscrever = new FileWriter(arquivoSaida);// arquivo para escrita
		arquivoSaida.createNewFile();// arquivo criado
		
		BufferedWriter bufferEscrita = new BufferedWriter(arquivoParaEscrever);
		
		while(st.hasMoreTokens())
		{
			// print all the tokens.
//			System.out.println("Remaining are : " + st.countTokens());
			System.out.println(st.nextToken());

			//bufferEscrita.write(st.nextToken());
			//bufferEscrita.flush();
		}
		bufferEscrita.close();		
		arquivoParaEscrever.close();
	}
	
}
