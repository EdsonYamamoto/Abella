package service;

import java.util.ArrayList;
import java.util.List;

import modelo.MetodoModelo;

public final class MetodoService {
	private static List<MetodoModelo> listaMetodos = new ArrayList<MetodoModelo>();
	private static List<MetodoModelo> listaBancoMetodos = new ArrayList<MetodoModelo>();
	
	public static List<MetodoModelo> getListaBancoMetodos() {
		return listaBancoMetodos;
	}
	public static void setListaBancoMetodos(List<MetodoModelo> listaBancoMetodos) {
		MetodoService.listaBancoMetodos = listaBancoMetodos;
	}
	private static MetodoModelo metodo = new MetodoModelo();
	
	public static List<MetodoModelo> getListaMetodos() {
		return listaMetodos;
	}
	public static void setListaMetodos(List<MetodoModelo> listaMetodos) {
		MetodoService.listaMetodos = listaMetodos;
	}
	public static MetodoModelo getMetodo() {
		return metodo;
	}
	
	public static void setMetodo(MetodoModelo metodo) {
		MetodoService.metodo = metodo;
	}
	public static void salvaMetodo(MetodoModelo metodo) {
		listaMetodos.add(metodo);
		MetodoService.metodo = new MetodoModelo();
	}
	
}
