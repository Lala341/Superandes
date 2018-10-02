package uniandes.isis2304.superandes.negocio;

/**
 * Interfaz para los métodos get de NoPerecedero.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VONoPerecedero {
	
	/**
	 * 
	 * @return id
	 */
	public long getId();
	
	/**
	 * 
	 * @return the Categoria
	 */
	public long getIdCategoria();

	/**
	 * @return the tipoCategoriaNoPerecedera
	 */
	public String getTipoCategoriaNoPerecedera();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de NoPerecedero
	 */
	public String toString();
}
