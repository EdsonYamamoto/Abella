package main;

public final class Configuracoes {

	/*
	 * Habilita inserção no banco de dados
	 * */
	private static boolean habilitaInsercaoBanco = false;
	/*
	 * Insercoes no banco
	 * */
	private static boolean inserirBancoListaIf = true;
	private static boolean inserirBancoListaExcecoes = true;
	private static boolean inserirBancoListaMetodos = true; 
	private static boolean inserirBancoListaConsultas = true;
	private static boolean inserirBancoListaArquivo = true;
	
	/*
	 * Habilita escrita em documentos de texto
	 * */
	private static boolean habilitaImpressaoLOG = false;
	
	/*
	 * Modo de impressao em documento
	 * */
	private static boolean imprimiListaIf = true;
	private static boolean imprimiListaExcecoes = true;
	private static boolean imprimiListaMetodos = true;
	private static boolean imprimiListaConsultas = true;
	private static boolean imprimiListaArquivo = true;
	
	/*
	 * Habilita impressao de script para o banco de dados NEO4J
	 * */
	private static boolean habilitaImpressaoScriptBancoNeo4J = true;
	/*
	 * imprimi todo o codigo do modo imprimir num arquivo especifico
	 * O modo simplificado de impressao deixa o texto de maneira simplificada
	 * */
	
	private static boolean habilitarModoImpressaoArquivo= false;
	private static boolean modoSimplificadoImpressao= false;
	
	/*
	 * imprimi todo o codigo do modo imprimir num arquivo especifico
	 * O modo simplificado de impressao deixa o texto de maneira simplificada
	 * */
	
	private static boolean habilitarModoFluxoPrograma= true;
	
	/*
	 * imprime todas os items armazenados na arvore
	 * */
	private static boolean habilitarModoImpressaoFluxoPrograma= false;
	
	/*
	 * Pasta que busca o arquivo para ser lido
	 * */
	//private static String diretorio = "C:/Users/Edson/Desktop/delphi"; 
	//private static String diretorio = "C:/Users/edson.kazumi/Desktop/teste"; 
	//private static String diretorio = "C:/XPCell/Fontes/fntXpCellProducaoSorocaba";
	private static String diretorio = "C:/Users/edson.kazumi/Desktop/1402/Locadora";

	/*
	 * Tipo do arquivo a ser lido
	 * */
	private static String extensaoArquivoEntrada = ".pas";
	
	/*
	 * Tipo do arquivo de saida
	 * */
	private static String extensaoArquivoSaida = ".txt";
	
	/*
	 * Pasta haverá o output de todos os arquivos
	 * */
	//private static String diretorioArquivoSaida = "C:/Users/Edson/Desktop/saidas"; 
	private static String diretorioArquivoSaida = "C:/Users/edson.kazumi/Desktop/testeSaida";
	
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
	public static boolean isHabilitarModoImpressaoArquivo() {
		return habilitarModoImpressaoArquivo;
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
	public static boolean isHabilitaImpressaoScriptBancoNeo4J() {
		return habilitaImpressaoScriptBancoNeo4J;
	}
	public static String getDiretorioArquivoSaida() {
		return diretorioArquivoSaida;
	}
	public static boolean isInserirBancoListaArquivo() {
		return inserirBancoListaArquivo;
	}
	public static boolean isImprimiListaArquivo() {
		return imprimiListaArquivo;
	}
	public static boolean isHabilitarModoFluxoPrograma() {
		return habilitarModoFluxoPrograma;
	}
	public static boolean isHabilitarModoImpressaoFluxoPrograma() {
		return habilitarModoImpressaoFluxoPrograma;
	}
	
}
