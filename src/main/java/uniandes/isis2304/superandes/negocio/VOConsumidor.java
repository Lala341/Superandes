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
	 * @return the fidelizacion
	 */
	public long getIdFidelizacion();

	/**
	 * @return the ventas
	 */
	public ArrayList<Venta> getVentas();
	
	/**
	 * @return Una cadena de caracteres con todos los atributos del Consumidor
	 */
	public String toString();

}
