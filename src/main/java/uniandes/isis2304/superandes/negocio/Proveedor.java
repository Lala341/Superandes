package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:44
 */
public class Proveedor {

	private int nit;
	private String nombre;
	private ArrayList<ProductoOfrecido> productoOfrecido;

	public Proveedor(){

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

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the productoOfrecido
	 */
	public ArrayList<ProductoOfrecido> getProductoOfrecido() {
		return productoOfrecido;
	}

	/**
	 * @param productoOfrecido the productoOfrecido to set
	 */
	public void setProductoOfrecido(ArrayList<ProductoOfrecido> productoOfrecido) {
		this.productoOfrecido = productoOfrecido;
	}

	

}