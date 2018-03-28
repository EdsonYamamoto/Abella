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
import javax.persistence.Table;

@Entity
@Table(name="ErrosXPCELL")
public class ExceptionsModelo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numLinha;
	private String mensagem;
	@Column(length = 1337)
	private String solocao;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "CODIGOMODELO_ID", referencedColumnName = "ID") })
	private MetodoModelo codigoModelo;

	public String getSolocao() {
		return solocao;
	}
	public void setSolocao(String solocao) {
		this.solocao = solocao;
	}
	
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public MetodoModelo getCodigoModelo() {
		return codigoModelo;
	}
	public void setCodigoModelo(MetodoModelo codigoModelo) {
		this.codigoModelo = codigoModelo;
	}
	@Override
	public String toString()
	{
		return this.codigoModelo+"\t"+ this.numLinha+"\t"+ this.mensagem;
	}
}
