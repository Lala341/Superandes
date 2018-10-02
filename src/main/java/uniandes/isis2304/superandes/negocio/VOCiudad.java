package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOCiudad {

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
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion);
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
