package main.java.superandes.negocio;

import java.util.ArrayList;
import java.util.Date;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:43
 */
public class Promocion {

	private String descripcion;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	private ArrayList<Sucursal> sucursales;

	public Promocion(){

	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFinalizacion
	 */
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	/**
	 * @param fechaFinalizacion the fechaFinalizacion to set
	 */
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	/**
	 * @return the sucursales
	 */
	public ArrayList<Sucursal> getSucursales() {
		return sucursales;
	}

	/**
	 * @param sucursales the sucursales to set
	 */
	public void setSucursales(ArrayList<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	

}