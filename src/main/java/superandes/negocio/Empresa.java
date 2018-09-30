package main.java.superandes.negocio;


/**
 * @author andre
 * @version 1.0
 * @created 30-sep-2018 10:00:32
 */
public class Empresa extends Consumidor {

	private String direccion;
	private int nit;

	public Empresa(){

	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the nit
	 */
	public int getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(int nit) {
		this.nit = nit;
	}

	

}