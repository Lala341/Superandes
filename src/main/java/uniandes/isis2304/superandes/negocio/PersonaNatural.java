package uniandes.isis2304.superandes.negocio;



/**
 * @version 1.0
 * @created 30-sep-2018 10:00:39
 */
public class PersonaNatural extends Consumidor implements VOPersonaNatural {

	private long idConsumidor;
	private long documentoIdentidad;

	public PersonaNatural(){

	}
	
	/**
	 * 
	 * @param idConsumidor - El identificador del Consumidor
	 * @param documentoIdentidad - documentoIdentidad de PersonaNatural
	 */
	public PersonaNatural (long idConsumidor, long documentoIdentidad) 
	{
		this.idConsumidor = idConsumidor;
		this.documentoIdentidad = documentoIdentidad;
	}

	
	/**
	 * 
	 * @return el id de consumidor
	 */
	public long getIdConsumidor() {
		return idConsumidor;
	}
	
	/**
	 * Modificar id consumidor
	 * @param idConsumidor id consumidor a modificar
	 */
	public void setIdConsumidor(long idConsumidor) {
		this.idConsumidor = idConsumidor;
	}

	/**
	 * @return the documentoIdentidad
	 */
	public long getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(long documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	/**
	 * @return Una cadena de caracteres con todos los atributos de PersonaNatural
	 */
	public String toString() 
	{
		return "PersonaNatural [documentoIdentidad=" + documentoIdentidad + "]";
	}

}