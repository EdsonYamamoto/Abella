package modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="IFsXPCELL")
public class IfModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "METODOMODELO_ID", referencedColumnName = "ID") })
	MetodoModelo metodoModelo;
	private int numLinha;
	@Column(length = 1337)
	private String condicao;
	@Column(name="dtCadastro")
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	
	
	
	
	
	
	public MetodoModelo getMetodoModelo() {
		return metodoModelo;
	}
	public void setMetodoModelo(MetodoModelo metodoModelo) {
		this.metodoModelo = metodoModelo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
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
		return this.metodoModelo+"\t"+this.numLinha+"\t"+ this.condicao;
	}
}
