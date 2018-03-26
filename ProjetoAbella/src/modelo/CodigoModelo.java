package modelo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MetodosXPCELL")
public class CodigoModelo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@OneToMany(cascade = CascadeType.ALL)
	private int id;
	private String unit;
	private String pasName;
	private String tipoMetodo;
	private int numLinha;
	private String metodo;
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPasName() {
		return pasName;
	}
	public void setPasName(String pasName) {
		this.pasName = pasName;
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
	public CodigoModelo()
	{
		
	}
	public CodigoModelo(String procedure,int numLinha)
	{
		this.metodo = procedure;
		this.numLinha = numLinha;
	}
	
	@Override
	public String toString()
	{
		return this.pasName+"\t"+ this.unit + "\t"+this.tipoMetodo+"\t"+this.numLinha+"\t"+this.metodo;
	}
}

