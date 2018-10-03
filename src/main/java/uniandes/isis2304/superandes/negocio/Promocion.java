package uniandes.isis2304.superandes.negocio;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @version 1.0
 * @created 30-sep-2018 10:00:43
 */
public class Promocion implements VOPromocion{

	private long id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFinalizacion;
	private String estado;

	public Promocion(){

	}
	public Promocion(long id, String nombre, Date fechaInicio, Date fechaFinalizacion, String estado){
		this.id=id;
		this.nombre=nombre;
		this.fechaInicio=fechaInicio;
		this.fechaFinalizacion=fechaFinalizacion;
		this.estado=estado;
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
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos
	 */
	public String toString() 
	{
		return "Promocion [id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio +  ", fechafin=" + fechaFinalizacion  +  ", estado=" + estado  +"]";
	}
	

}