package main;

public final class Configuracoes {
	
	
	// lista todos os IF's do codigo
	private static boolean listarIf = true;
	// lista todos as exceptions do codigo 
	private static boolean listarExcecoes = true;
	// lista todas as funções e procedures
	private static boolean listarMetodos = true;
	// lista todos as consultas de data source do codigo 
	private static boolean listarConsultas = false;
	//modo simplificado retorna o tipo de coisa encontrado na linha como por exemplo if's e else's
	private static boolean modoSimplificado= false;
	//imprimi diretamente no console
	private static boolean modoImprimir= false;
	//imprimi todo o codigo do modo imprimir num arquivo especifico
	private static boolean escritaEmArquivo= false;
	//imprimi procura
	private static boolean procuraEmArquivo= false;
	
	//a pasta que o programa vai pegar as informações
	//private static String diretorio = "C:/Users/edson.kazumi/Desktop/teste"; //teste
	private static String diretorio = "C:/XPCell/Fontes/fntXpCellProducaoSorocaba";
	
	
	/*
	 * 1 - drop e create
	 * 2 - create
	 * 3 - drop
	 * default nao faz nada
	 * */
	private static int acoesBanco= 1;
	
	
	/*
	 * Metodos get para variaveis
	 * */

	public static int getAcoesBanco() {
		return acoesBanco;
	}
	public static String getDiretorio() {
		return diretorio;
	}
	public static boolean isListarIf() {
		return listarIf;
	}
	public static boolean isListarExcecoes() {
		return listarExcecoes;
	}
	public static boolean isListarMetodos() {
		return listarMetodos;
	}
	public static boolean isListarConsultas() {
		return listarConsultas;
	}
	public static boolean isModoSimplificado() {
		return modoSimplificado;
	}
	public static boolean isModoImprimir() {
		return modoImprimir;
	}
	public static boolean isEscritaEmArquivo() {
		return escritaEmArquivo;
	}
	public static boolean isProcuraEmArquivo() {
		return procuraEmArquivo;
	}
	
	
	
}
