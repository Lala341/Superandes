package uniandes.isis2304.superandes.negocio;

/**
 * Interfaz para los métodos get de Categoria.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */

public interface VOCategoria {

	/**
	 * 
	 * @return el id
	 */
	public long getId();

	/**
	 * @return the Nombre
	 */
	public String getNombre();
	
	/**
	 * @return el tipoDeAlmacenamiento
	 */
	public String getTipoDeAlmacenamiento();

	/**
	 * @return el tipoDeManejo
	 */
	public String getTipoDeManejo();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Categoria
	 */
	public String toString();
}
