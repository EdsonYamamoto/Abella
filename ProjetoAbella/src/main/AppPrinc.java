package main;

import java.io.File;
import java.io.IOException;
import java.lang.String;


public class AppPrinc {
	
	
	//cria o app principals
	public static void main(String[] args)
	{
		try {
			AppPrinc programa = new AppPrinc();	
			System.out.println("Sucesso em ler os arquivos programa");
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
				Core.reescreveCodigo(arquivos);
			}
			if(arquivos.length()==0)
				arquivos.delete();
		}
		if(Configuracoes.isHabilitaInsercaoBanco())
			Features.inserirDadosBanco();
		if(Configuracoes.isHabilitaImpressaoLOG())
			Features.impressaoDados();
		Features.leQuery();
	}
}

