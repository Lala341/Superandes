package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:33
 */
public class Estante implements VOEstante {

	

	private double peso;
	private double volumen;
	private int capacidadTotal;
	private int cantidadProductos;
	private int nivelDeAbastecimiento;
	private String nombre;
	private String equipamientoAdicional;
	private long id;
	private long idSucursal;
	private long nivelReorden;

	public Estante(){

	}
	
	/**
	 * @param idEstante - El identificador del estante
	 * @param cantidadProductos - la cantidad de productos en el estante
	 * @param capacidadTotal - La capacidad de el estante
	 * @param peso - El peso manejado en el estante
	 * @param volumen - El volumen manejado en el estante
	 * @param tipoProducto - El tipo de producto que maneja el estante
	 * @param equipamientoAdicional el equipamiento adicional del estante
	 * @param nivelDeAbastecimiento nivel de abastecimiento del estante
	 * @param idSucursal
	 */
	public Estante (long idEstante, int cantidadProductos, int capacidadTotal, double peso, double volumen, String tipoProducto, String equipamientoAdicional, long nivelReorden, int nivelDeAbastecimiento, long idSucursal) 
	{
		id = idEstante;
		this.peso = peso;
		this.volumen = volumen;
		this.cantidadProductos = cantidadProductos;
		this.nivelDeAbastecimiento = nivelDeAbastecimiento;
		this.capacidadTotal = capacidadTotal;
		this.nombre = nombre;
		this.equipamientoAdicional = equipamientoAdicional;
		this.idSucursal = idSucursal;
		this.nivelReorden=nivelReorden;
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
	 * @return the nivelDeAbastecimiento
	 */
	public int getNivelDeAbastecimiento() {
		return nivelDeAbastecimiento;
	}

	/**
	 * @param nivelDeAbastecimiento the nivelDeAbastecimiento to set
	 */
	public void setNivelDeAbastecimiento(int nivelDeAbastecimiento) {
		this.nivelDeAbastecimiento = nivelDeAbastecimiento;
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
	 * @return the equipamientoAdicional
	 */
	public String getEquipamientoAdicional() {
		return equipamientoAdicional;
	}

	/**
	 * @param equipamientoAdicional the equipamientoAdicional to set
	 */
	public void setEquipamientoAdicional(String equipamientoAdicional) {
		this.equipamientoAdicional = equipamientoAdicional;
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
	 * @return the sucursal
	 */
	public long getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the sucursal to set
	 */
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	

	
	/**
	 * @return the nivelReorden
	 */
	public long getNivelReorden() {
		return nivelReorden;
	}

	/**
	 * @param nivelReorden the nivelReorden to set
	 */
	public void setNivelReorden(long nivelReorden) {
		this.nivelReorden = nivelReorden;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Estante [id=" + id + ", nombre=" + nombre + ", peso=" + peso +", volumen=" + volumen + ", idSucursal=" + idSucursal
				+ ", cantidadProductos=" + cantidadProductos + ", capacidadTotal=" + capacidadTotal + ", equipamientoAdicional=" + equipamientoAdicional +", nivelDeAbastecimiento="+ nivelDeAbastecimiento +"]";
	}

}