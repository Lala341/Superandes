package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:29
 */
public class Consumidor implements VOConsumidor {

	private long id;
	private String nombre;
	private String correoElectronico;
	private long idFidelizacion;
	private ArrayList<Venta> ventas;

	public Consumidor(){

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
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}

	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	/**
	 * @return the fidelizacion
	 */
	public long getIdFidelizacion() {
		return idFidelizacion;
	}

	/**
	 * @param idFidelizacion the fidelizacion to set
	 */
	public void setFidelizacion(long idFidelizacion) {
		this.idFidelizacion = idFidelizacion;
	}

	/**
	 * @return the ventas
	 */
	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	/**
	 * @param ventas the ventas to set
	 */
	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de Consumidor
	 */
	public String toString() 
	{
		return "Consumidor [id=" + id + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", idFidelizacion=" + idFidelizacion
				+ ", ventas=" + ventas + "]";
	}

}