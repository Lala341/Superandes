package uniandes.isis2304.superandes.negocio;


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
	public String getFechaInicio();
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio);
	/**
	 * @return the fechaFinalizacion
	 */
	public String getFechaFinalizacion();
	/**
	 * @param fechaFinalizacion the fechaFinalizacion to set
	 */
	public void setFechaFinalizacion(String fechaFinalizacion);
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Promocion
	 */
	public String toString();

}
