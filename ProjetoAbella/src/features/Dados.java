package features;

import main.Configuracoes;
import modelo.ConsultaModelo;
import modelo.ExceptionsModelo;
import modelo.IfModelo;
import modelo.MetodoModelo;
import service.ConsultaService;
import service.ExceptionService;
import service.IfService;
import service.MetodoService;

public class Dados {
	public static void impressaoDados()
	{
		if (Configuracoes.isImprimiListaMetodos()) 
			for (MetodoModelo c : MetodoService.getListaMetodos()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaExcecoes())
			for (ExceptionsModelo c : ExceptionService.getListaExceptions()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaIf())
			for (IfModelo c : IfService.getListaIf()) 
				System.out.println(c);
		if(Configuracoes.isImprimiListaConsultas())
			for (ConsultaModelo c : ConsultaService.getListaConsultas()) 
				System.out.println(c);
	}
}
