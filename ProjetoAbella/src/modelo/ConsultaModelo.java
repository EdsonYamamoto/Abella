package modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="ConsultaBancoXPCELL")
public class ConsultaModelo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numLinha;
	
	@Lob
	@Column
	private String consulta;
	@Column(name="dtCadastro")
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	
	public ConsultaModelo()
	{
		numLinha=0;
		consulta="";
	}
	
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
		return ""+ this.consulta;
	}
}
