package service;

import java.util.ArrayList;
import java.util.List;

import modelo.ConsultaModelo;

public class ConsultaService {
	private static List<ConsultaModelo> listaConsultas = new ArrayList<ConsultaModelo>();
	private static List<ConsultaModelo> listaBancoConsultas = new ArrayList<ConsultaModelo>();
	private static ConsultaModelo consulta = new ConsultaModelo();
	
	public static List<ConsultaModelo> getListaBancoConsultas() {
		return listaBancoConsultas;
	}
	public static void setListaBancoConsultas(List<ConsultaModelo> listaBancoConsultas) {
		ConsultaService.listaBancoConsultas = listaBancoConsultas;
	}
	public static List<ConsultaModelo> getListaConsultas() {
		return listaConsultas;
	}
	public static void setListaConsultas(List<ConsultaModelo> listaConsultas) {
		ConsultaService.listaConsultas = listaConsultas;
	}
	public static ConsultaModelo getConsulta() {
		return consulta;
	}
	public static void setConsulta(ConsultaModelo consulta) {
		ConsultaService.consulta = consulta;
	}
	public static void salvaConsulta(ConsultaModelo consulta) {
		listaConsultas.add(consulta);
		ConsultaService.consulta = new ConsultaModelo();
	}
}
