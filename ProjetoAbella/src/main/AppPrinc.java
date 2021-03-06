package main;

import java.io.File;
import java.io.IOException;
import java.lang.String;

import features.BancoDados;
import features.BancoDadosGrafo;
import features.Dados;


public class AppPrinc {
	
	
	//cria o app principals
	public static void main(String[] args)
	{
		try {
			System.out.println("INICIADO PROGRAMA");
			AppPrinc programa = new AppPrinc();	
			System.out.println("Programa : "+programa+" finalizando");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AppPrinc() throws IOException {
		File file = new File(Configuracoes.getDiretorio());
		File afile[] = file.listFiles();
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			if(arquivos.getName().endsWith(Configuracoes.getExtensaoArquivoEntrada()))
			{
				CoreExtrator.reescreveCodigo(arquivos);
			}
			if(arquivos.length()==0)
				arquivos.delete();
		}

		System.out.println("Feito a leitura de todos os arquivos");
		
		if(Configuracoes.isHabilitaInsercaoBanco())
		{
			BancoDados.inserirDadosBanco();
			System.out.println("Inserido todas informações no banco");
		}
		if(Configuracoes.isHabilitaImpressaoLOG())
			Dados.impressaoDados();
		if(Configuracoes.isHabilitaImpressaoScriptBancoNeo4J())
		{
			BancoDadosGrafo.impressaoDadosBancoNeo4j();
			System.out.println("Impresso script");
		}

		BancoDados.leQueryEEscreveNovosDados();
	}
}

