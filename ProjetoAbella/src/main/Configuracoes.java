package main;

public final class Configuracoes {

	/*
	 * Habilita inserção no banco de dados
	 * */
	private static boolean habilitaInsercaoBanco = true;
	/*
	 * Insercoes no banco
	 * */
	private static boolean inserirBancoListaIf = true;
	private static boolean inserirBancoListaExcecoes = true;
	private static boolean inserirBancoListaMetodos = true; 
	private static boolean inserirBancoListaConsultas = true;
	

	/*
	 * Habilita escrita em documentos de texto
	 * */
	private static boolean habilitaImpressaoLOG = false;
	/*
	 * Modo de impressao em documento
	 * */
	private static boolean imprimiListaIf = false;
	private static boolean imprimiListaExcecoes = false;
	private static boolean imprimiListaMetodos = false;
	private static boolean imprimiListaConsultas = false;
	
	
	//modo simplificado retorna o tipo de coisa encontrado na linha como por exemplo if's e else's
	private static boolean modoSimplificadoImpressao= false;
	//imprimi diretamente no console
	private static boolean modoImprimir= false;
	//imprimi todo o codigo do modo imprimir num arquivo especifico
	private static boolean escritaEmArquivo= false;
	//imprimi procura
	private static boolean procuraEmArquivo= false;
	
	
	//a pasta que o programa vai pegar as informações
	private static String diretorio = "C:/Users/Edson/Desktop/delphi"; 
	//private static String diretorio = "C:/Users/edson.kazumi/Desktop/teste"; 
	//private static String diretorio = "C:/XPCell/Fontes/fntXpCellProducaoSorocaba";
	
	//Tipo do arquivo a ser lido
	private static String extensaoArquivoEntrada = ".pas";
	//tipo de arquivo de saida
	private static String extensaoArquivoSaida = ".txt";
	
	
	public static String getExtensaoArquivoSaida() {
		return extensaoArquivoSaida;
	}
	/*
	 * 1 - drop e create
	 * 2 - create
	 * 3 - drop
	 * default nao faz nada
	 * */
	private static int acoesBanco= 1;
	
	

	public static boolean isImprimiListaIf() {
		return imprimiListaIf;
	}
	public static boolean isImprimiListaExcecoes() {
		return imprimiListaExcecoes;
	}
	public static boolean isImprimiListaMetodos() {
		return imprimiListaMetodos;
	}
	public static boolean isImprimiListaConsultas() {
		return imprimiListaConsultas;
	}
	public static boolean isInserirBancoListaIf() {
		return inserirBancoListaIf;
	}
	public static boolean isInserirBancoListaExcecoes() {
		return inserirBancoListaExcecoes;
	}
	public static boolean isInserirBancoListaMetodos() {
		return inserirBancoListaMetodos;
	}
	public static boolean isInserirBancoListaConsultas() {
		return inserirBancoListaConsultas;
	}
	public static boolean isModoSimplificadoImprimir() {
		return modoSimplificadoImpressao;
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
	public static String getDiretorio() {
		return diretorio;
	}
	public static String getExtensaoArquivoEntrada() {
		return extensaoArquivoEntrada;
	}
	public static int getAcoesBanco() {
		return acoesBanco;
	}
	public static boolean isHabilitaInsercaoBanco() {
		return habilitaInsercaoBanco;
	}
	public static boolean isModoSimplificadoImpressao() {
		return modoSimplificadoImpressao;
	}
	public static boolean isHabilitaImpressaoLOG() {
		return habilitaImpressaoLOG;
	}
	
	
	/*
	 * Metodos get para variaveis
	 * */
	
}
