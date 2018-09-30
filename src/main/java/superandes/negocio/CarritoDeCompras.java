package main.java.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:26
 */
public class CarritoDeCompras {

	private String estado;
	private Consumidor consumidor;
	private ArrayList<ProductoTransaccion> productosTransaccion;

	public CarritoDeCompras(){

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
	 * @return the consumidor
	 */
	public Consumidor getConsumidor() {
		return consumidor;
	}

	/**
	 * @param consumidor the consumidor to set
	 */
	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}

	/**
	 * @return the productosTransaccion
	 */
	public ArrayList<ProductoTransaccion> getProductosTransaccion() {
		return productosTransaccion;
	}

	/**
	 * @param productosTransaccion the productosTransaccion to set
	 */
	public void setProductosTransaccion(ArrayList<ProductoTransaccion> productosTransaccion) {
		this.productosTransaccion = productosTransaccion;
	}
	

	

}