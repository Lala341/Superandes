package uniandes.isis2304.superandes.negocio;

/**
 * Interfaz para los m�todos get de PersonaNatural.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOPersonaNatural {

	/**
	 * @return the documentoIdentidad
	 */
	public int getDocumentoIdentidad();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos de PersonaNatural
	 */
	public String toString();

}
