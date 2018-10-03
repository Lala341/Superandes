package uniandes.isis2304.superandes.negocio;

import java.sql.Date;

/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPromocion {

	

	/**
	 * @return the id
	 */
	public long getId();
	/**
	 * @param id the id to set
	 */
	public void setId(long id);
	/**
	 * @return the nombre
	 */
	public String getNombre();
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre);
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio();
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio);
	/**
	 * @return the fechaFinalizacion
	 */
	public Date getFechaFinalizacion();
	/**
	 * @param fechaFinalizacion the fechaFinalizacion to set
	 */
	public void setFechaFinalizacion(Date fechaFinalizacion);
	
	/**
	 * @return the estado
	 */
	public String getEstado();
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado);
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
