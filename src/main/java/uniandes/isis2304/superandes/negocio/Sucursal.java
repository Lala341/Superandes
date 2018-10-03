package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:45
 */
public class Sucursal implements VOSucursal {

	private double tamanho;
	private String tipoDeMercado;
	private double ventasTotales;
	private long id;
	private long idCiudad;
	private String direccion;
	private String nombre;
	/**
	 * Constructor por defecto
	 */
	public Sucursal(){

	}
	
	/**
	 * Constructor con valores
	 * @param id - El id de la sucursal
	 * @param tamanho - El tamanho de la sucursal
	 * @param tipoDeMercado - tipo de mercado
	 * @param ventasTotales - ventas totales
	 * @param idCiudad - id ciudad
	 */
	public Sucursal (long idSucursal, String nombre, long tamanho, String tipoDeMercado, double ventasTotales, long idCiudad) 
	{
		this.id = idSucursal;
		this.nombre= nombre;
		this.tamanho = tamanho;
		this.tipoDeMercado = tipoDeMercado;
		this.ventasTotales = ventasTotales;
		this.idCiudad = idCiudad;
	}


	/**
	 * @return the tamanho
	 */
	public double getTamanho() {
		return tamanho;
	}


	/**
	 * @param tamanho the tamanho to set
	 */
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}


	/**
	 * @return the tipoDeMercado
	 */
	public String getTipoDeMercado() {
		return tipoDeMercado;
	}


	/**
	 * @param tipoDeMercado the tipoDeMercado to set
	 */
	public void setTipoDeMercado(String tipoDeMercado) {
		this.tipoDeMercado = tipoDeMercado;
	}


	/**
	 * @return the ventasTotales
	 */
	public double getVentasTotales() {
		return ventasTotales;
	}


	/**
	 * @param ventasTotales the ventasTotales to set
	 */
	public void setVentasTotales(double ventasTotales) {
		this.ventasTotales = ventasTotales;
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
	 * @return the ciudad
	 */
	public long getIdCiudad() {
		return idCiudad;
	}


	/**
	 * @param idCiudad the ciudad to set
	 */
	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
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
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString() 
	{
		return "Sucursal [id=" + id + ", tamanho=" + tamanho + ", tipoDeMercado=" + tipoDeMercado + ", ventasTotales=" + ventasTotales
				+ ", idCiudad=" + idCiudad + "]";
	}
	

}