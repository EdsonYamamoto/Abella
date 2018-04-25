package main;

import java.io.File;
import java.io.IOException;
import java.lang.String;

import compiladorPascal.GeneratorPascal;
import features.BancoDados;
import features.BancoDadosGrafo;
import features.Dados;
import pacoteGrafo.Graph;
import pacoteGrafo.Node;


public class AppPrinc {
	
	
	//cria o app principals
	public static void main(String[] args)
	{
		try {
			System.out.println("INICIADO PROGRAMA");
			AppPrinc programa = new AppPrinc();	
			System.out.println("Programa : "+programa+" finalizado com sucesso");
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
				ExtratorLexico.extrator(arquivos);
				//CoreExtrator.reescreveCodigo(arquivos);
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
		
		if(Configuracoes.isHabilitarModoFluxoPrograma())
		{
			FluxoExtrator.arvore = new Graph();
			i = 0;
			for (int j = afile.length; i < j; i++) {
				File arquivos = afile[i];
				if(arquivos.getName().endsWith(Configuracoes.getExtensaoArquivoEntrada()))
					FluxoExtrator.escreveFluxoPrograma(arquivos);
				
				if(arquivos.length()==0)
					arquivos.delete();
			}
		}
		
		if(Configuracoes.isHabilitaImpressaoScriptBancoNeo4J())
		{
			BancoDadosGrafo.impressaoDadosBancoNeo4j();
			System.out.println("Impresso script");
		}
	}
}

