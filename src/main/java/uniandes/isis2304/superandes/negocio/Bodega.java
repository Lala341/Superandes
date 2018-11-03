package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:24
 */
public class Bodega implements VOBodega {

	
	public double volumen;
	public double peso;
	public int capacidadTotal;
	public int cantidadProductos;
	public long id;
	public long sucursal;
	public double nivelReorden;
	public String tipoProducto;
	public String nombre;
	
	/**
	 * Constructor por defecto
	 */
	
	public Bodega()
	{
		
	}
	
	/**
	 * @param idBodega - El identificador del bodega
	 * @param cantidadProductos - la cantidad de productos en la bodega
	 * @param capacidadTotal - La capacidad de la bodega
	 * @param peso - El peso manejado en la bodega
	 * @param volumen - El volumen manejado en la bodega
	 * @param tipoProducto - El tipo de producto que maneja la bodega
	 * @param nivelDeAbastecimiento
	 * @param sucursal
	 */
	public Bodega (long idBodega,String nombre,  int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, double nivelDeReorden, long idSucursal) 
	{
		this.id=idBodega;
		this.nombre=nombre;
		this.volumen = volumen;
		this.peso = peso;
		this.capacidadTotal = capacidadTotal;
		this.cantidadProductos = cantidadProductos;
		this.sucursal = idSucursal;
		this.nivelReorden= nivelDeReorden;
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
	public  double getNivelDeReorden() {
		return nivelReorden;
	}

	/**
	 * @return the sucursal
	 */
	public long getIdSucursal() {
		return sucursal;
	}

	/**
	 * @param sucursal the sucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.sucursal = idSucursal;
	}

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Bodega [id=" + id + ", peso=" + peso + ", volumen=" + volumen + ", sucursal=" + sucursal
				+ ", cantidadProductos=" + cantidadProductos + ", capacidadTotal=" + capacidadTotal +"]";
	}

}