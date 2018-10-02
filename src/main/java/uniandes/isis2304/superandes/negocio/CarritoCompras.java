package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:26
 */
public class CarritoCompras implements VOCarritoCompras{

	private long id;
	private String estado;
	private long consumidor;

	public CarritoCompras(){

	}
	public CarritoCompras(long id, String estado,long consumidor){
		this.id=id;
		this.estado=estado;
		this.consumidor=consumidor;
	}
	

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}



	/**
	 * @return the consumidor
	 */
	public long getConsumidor() {
		return consumidor;
	}



	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(long consumidor) {
		this.consumidor = consumidor;
	}



	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "CarritoCompras [id=" + id + ", estado=" + estado + ", consumidor=" + consumidor +"]";
	}
	

	

}