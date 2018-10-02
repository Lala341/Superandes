package uniandes.isis2304.superandes.negocio;


/**
 * @version 1.0
 * @created 30-sep-2018 10:00:39
 */
public class PersonaNatural extends Consumidor implements VOPersonaNatural {

	private int documentoIdentidad;

	public PersonaNatural(){

	}

	/**
	 * @return the documentoIdentidad
	 */
	public int getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(int documentoIdentidad) {
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