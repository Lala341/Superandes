package uniandes.isis2304.superandes.negocio;


/**
 * Interfaz para los métodos get de Bodega.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOFidelizacion {

	

	/**
	 * @return the cantidadPuntos
	 */
	public int getCantidadPuntos();

	/**
	 * @param cantidadPuntos the cantidadPuntos to set
	 */
	public void setCantidadPuntos(int cantidadPuntos);
	

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @param id the id to set
	 */
	public void setId(long id);

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Bodega
	 */
	public String toString();

}
