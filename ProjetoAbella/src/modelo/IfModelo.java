package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity(name="IFsXPCELL")
public class IfModelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numLinha;
	@Column(length = 1337)
	private String condicao;
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CODIGOMODELO_ID", referencedColumnName = "ID") })
	MetodoModelo codigoModelo;
	
	public MetodoModelo getCodigoModelo() {
		return codigoModelo;
	}
	public void setCodigoModelo(MetodoModelo codigoModelo) {
		this.codigoModelo = codigoModelo;
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

	@Override
	public String toString()
	{
		return this.numLinha+"\t"+ this.condicao;
	}
}
