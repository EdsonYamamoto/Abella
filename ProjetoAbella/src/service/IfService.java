package service;

import java.util.ArrayList;
import java.util.List;

import modelo.IfModelo;

public class IfService {
	private static List<IfModelo> listaIf = new ArrayList<IfModelo>();
	private static List<IfModelo> listaBancoIf = new ArrayList<IfModelo>();
	
	public static List<IfModelo> getListaBancoIf() {
		return listaBancoIf;
	}

	public static void setListaBancoIf(List<IfModelo> listaBancoIf) {
		IfService.listaBancoIf = listaBancoIf;
	}

	private static IfModelo If = new IfModelo();

	public static List<IfModelo> getListaIf() {
		return listaIf;
	}

	public static void setListaIfs(List<IfModelo> listaIf) {
		IfService.listaIf = listaIf;
	}

	public static IfModelo getIf() {
		return If;
	}

	public static void setIf(IfModelo If) {
		IfService.If = If;
	}

	public static void salvaIf(IfModelo If) {
		listaIf.add(If);
		IfService.If = new IfModelo();
	}
}
