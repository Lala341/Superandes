package main.java.uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:46
 */
public class Venta {

	private long id;
	private String fecha;
	private String formaDePago;
	private double valorTotal;
	private Factura factura;
	private ArrayList<ProductoTransaccion> productosTransaccion;


	public Venta(){

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
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the formaDePago
	 */
	public String getFormaDePago() {
		return formaDePago;
	}


	/**
	 * @param formaDePago the formaDePago to set
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}


	/**
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}


	/**
	 * @param valorTotal the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}


	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
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