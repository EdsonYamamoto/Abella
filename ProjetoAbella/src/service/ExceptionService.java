package service;

import java.util.ArrayList;
import java.util.List;

import modelo.ExceptionsModelo;

public class ExceptionService {
	private static List<ExceptionsModelo> listaExceptions = new ArrayList<ExceptionsModelo>();
	private static List<ExceptionsModelo> listaBancoExceptions = new ArrayList<ExceptionsModelo>();
	private static ExceptionsModelo exceptions = new ExceptionsModelo();
	
	public static List<ExceptionsModelo> getListaBancoExceptions() {
		return listaBancoExceptions;
	}

	public static void setListaBancoExceptions(List<ExceptionsModelo> listaBancoExceptions) {
		ExceptionService.listaBancoExceptions = listaBancoExceptions;
	}

	public static List<ExceptionsModelo> getListaExceptions() {
		return listaExceptions;
	}

	public static void setListaExceptionss(List<ExceptionsModelo> listaExceptions) {
		ExceptionService.listaExceptions = listaExceptions;
	}

	public static ExceptionsModelo getExceptions() {
		return exceptions;
	}

	public static void setExceptions(ExceptionsModelo Exceptions) {
		ExceptionService.exceptions = Exceptions;
	}

	public static void salvaException(ExceptionsModelo exceptions) {
		listaExceptions.add(exceptions);
		ExceptionService.exceptions = new ExceptionsModelo();
	}
}
