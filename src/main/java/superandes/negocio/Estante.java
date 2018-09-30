package main.java.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:33
 */
public class Estante {

	public static final String NIVEL_DE_REORDEN="";
	

	private double peso;
	private double volumen;
	private int capacidadTotal;
	private int cantidadProductos;
	private int nivelDeAbastecimiento;
	private String nombre;
	private String equipamientoAdicional;
	private long id;
	private Sucursal sucursal;
	private Categoria categoria;

	public Estante(){

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
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the nivelDeReorden
	 */
	public static String getNivelDeReorden() {
		return NIVEL_DE_REORDEN;
	}

	

}