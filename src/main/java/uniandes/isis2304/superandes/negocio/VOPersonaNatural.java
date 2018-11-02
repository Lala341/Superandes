package uniandes.isis2304.superandes.negocio;

/**
 * Interfaz para los métodos get de PersonaNatural.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPersonaNatural {

	/**
	 * 
	 * @return el id de consumidor
	 */
	public long getIdConsumidor();
	
	/**
	 * Modificar id consumidor
	 * @param idConsumidor id consumidor a modificar
	 */
	public void setIdConsumidor(long idConsumidor);

	/**
	 * @return the documentoIdentidad
	 */
	public long getDocumentoIdentidad();

	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(long documentoIdentidad);

	/**
	 * @return Una cadena de caracteres con todos los atributos de PersonaNatural
	 */
	public String toString();

}
