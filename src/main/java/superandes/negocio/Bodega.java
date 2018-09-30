package main.java.superandes.negocio;

import java.util.ArrayList;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:24
 */
public class Bodega {

	public static final String NIVEL_DE_REORDEN="";
	
	private double volumen;
	private double peso;
	private int capacidadTotal;
	private int cantidadProductos;
	private long id;
	private Sucursal sucursal;
	private ArrayList<Producto> productos;
	
	public Bodega(){

	}

	/**
	 * @return the volumen
	 */
	public double getVolumen() {
		return volumen;
	}

	/**
	 * @param volumen the volumen to set
	 */
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	/**
	 * @return the peso
	 */
	public double getPeso() {
		return peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(double peso) {
		this.peso = peso;
	}

	/**
	 * @return the capacidadTotal
	 */
	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	/**
	 * @param capacidadTotal the capacidadTotal to set
	 */
	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}

	/**
	 * @return the cantidadProductos
	 */
	public int getCantidadProductos() {
		return cantidadProductos;
	}

	/**
	 * @param cantidadProductos the cantidadProductos to set
	 */
	public void setCantidadProductos(int cantidadProductos) {
		this.cantidadProductos = cantidadProductos;
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
	 * @return the nivelDeReorden
	 */
	public static String getNivelDeReorden() {
		return NIVEL_DE_REORDEN;
	}

	/**
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the productos
	 */
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Bodega [id=" + id + ", peso=" + peso + ", volumen=" + volumen + ", sucursal=" + sucursal.toString()
				+ ", cantidadProductos=" + cantidadProductos + ", capacidadTotal=" + capacidadTotal +", productos=" + productos +"]";
	}

}