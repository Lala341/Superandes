package main.java.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:37
 */
public class OrdenDePedido {

	private String fechaEntrega;
	private int calificacion;
	private String estado;
	private String fecha;
	private long id;
	private Proveedor proveedor;
	private ArrayList<ProductoOfrecidoTransaccion> productosOfrecidosTransaccion;

	public OrdenDePedido(){

	}

	/**
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * @return the calificacion
	 */
	public int getCalificacion() {
		return calificacion;
	}

	/**
	 * @param calificacion the calificacion to set
	 */
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
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
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedores the proveedores to set
	 */
	public void setProveedores(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the productosOfrecidosTransaccion
	 */
	public ArrayList<ProductoOfrecidoTransaccion> getProductosOfrecidosTransaccion() {
		return productosOfrecidosTransaccion;
	}

	/**
	 * @param productosOfrecidosTransaccion the productosOfrecidosTransaccion to set
	 */
	public void setProductosOfrecidosTransaccion(ArrayList<ProductoOfrecidoTransaccion> productosOfrecidosTransaccion) {
		this.productosOfrecidosTransaccion = productosOfrecidosTransaccion;
	}

	

}