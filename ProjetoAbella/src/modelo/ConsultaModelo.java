package modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ConsultaModelo {
	private int numLinha;
	private String consulta;
	@Column(name="dtCadastro")
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	@Override
	public String toString()
	{
		return this.numLinha+"\t"+ this.consulta;
	}
}
