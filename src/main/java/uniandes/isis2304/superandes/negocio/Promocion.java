package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;
import java.util.Date;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:43
 */
public class Promocion implements VOPromocion{

	private long id;
	private String nombre;
	private String fechaInicio;
	private String fechaFinalizacion;

	public Promocion(){

	}
	public Promocion(long id, String nombre, String fechaInicio, String fechaFinalizacion){
		this.id=id;
		this.nombre=nombre;
		this.fechaInicio=fechaInicio;
		this.fechaFinalizacion=fechaFinalizacion;
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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFinalizacion
	 */
	public String getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	/**
	 * @param fechaFinalizacion the fechaFinalizacion to set
	 */
	public void setFechaFinalizacion(String fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Promocion [id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio +  ", fechafin=" + fechaFinalizacion  +"]";
	}
	

}