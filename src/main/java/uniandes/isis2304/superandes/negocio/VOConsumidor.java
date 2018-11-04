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

	public void setId(long id);
	/**
	 * @return the nombre
	 */
	public String getNombre();

	public void setNombre(String nombre);
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico();

	public void setCorreoElectronico(String correo);
	/**
	 * @return the tipoconsumidor
	 */
	public String getTipo();
	/**
	 * @param tipoconsumidor the tipoconsumidor to set
	 */
	public void setTipo(String tipo);
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Consumidor
	 */
	public String toString();

}
