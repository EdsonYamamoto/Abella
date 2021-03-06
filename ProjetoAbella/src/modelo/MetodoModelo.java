package modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="MetodosXPCELL")
public class MetodoModelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(cascade = CascadeType.ALL)
	private int id;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "ARQUIVOMODELO_ID", referencedColumnName = "ID") })
	private ArquivoModelo arquivoModelo;
	private String unit;
	private String tipoMetodo;
	private int numLinha;
	private String metodo;
	@Column(name="dtCadastro")
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;
	
	
	
	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String gettipoMetodo() {
		return tipoMetodo;
	}
	public void settipoMetodo(String tipoMetodo) {
		this.tipoMetodo = tipoMetodo;
	}
	public int getNumLinha() {
		return numLinha;
	}
	public void setNumLinha(int numLinha) {
		this.numLinha = numLinha;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public MetodoModelo()
	{
		
	}
	public MetodoModelo(String procedure,int numLinha)
	{
		this.metodo = procedure;
		this.numLinha = numLinha;
	}
	
	
	
	public ArquivoModelo getArquivoModelo() {
		return arquivoModelo;
	}
	public void setArquivoModelo(ArquivoModelo arquivoModelo) {
		this.arquivoModelo = arquivoModelo;
	}
	@Override
	public String toString()
	{
		return this.unit + "\t"+this.tipoMetodo+"\t"+this.numLinha+"\t"+this.metodo;
	}
	
}

