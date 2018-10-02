package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * Interfaz para los métodos get de Consumidor.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOConsumidor {

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the nombre
	 */
	public String getNombre();

	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico();

	/**
	 * @return the tipo
	 */
	public String getTipo();
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo);
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Consumidor
	 */
	public String toString();

}
