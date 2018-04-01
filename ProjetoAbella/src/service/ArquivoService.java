package service;

import java.util.ArrayList;
import java.util.List;

import modelo.ArquivoModelo;

public class ArquivoService {
	private static List<ArquivoModelo> listaArquivos = new ArrayList<ArquivoModelo>();
	private static List<ArquivoModelo> listaBancoArquivos = new ArrayList<ArquivoModelo>();
	private static ArquivoModelo Arquivo = new ArquivoModelo();
	
	public static List<ArquivoModelo> getListaBancoArquivos() {
		return listaBancoArquivos;
	}
	public static void setListaBancoArquivos(List<ArquivoModelo> listaBancoArquivos) {
		ArquivoService.listaBancoArquivos = listaBancoArquivos;
	}
	public static List<ArquivoModelo> getListaArquivos() {
		return listaArquivos;
	}
	public static void setListaArquivos(List<ArquivoModelo> listaArquivos) {
		ArquivoService.listaArquivos = listaArquivos;
	}
	public static ArquivoModelo getArquivo() {
		return Arquivo;
	}
	public static void setArquivo(ArquivoModelo Arquivo) {
		ArquivoService.Arquivo = Arquivo;
	}
	public static void salvaArquivo(ArquivoModelo Arquivo) {
		listaArquivos.add(Arquivo);
		ArquivoService.Arquivo = new ArquivoModelo();
	}
}
