
package uniandes.isis2304.superandes.negocio;

import java.util.ArrayList;

/**
 * Interfaz para los métodos get de Sucursal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 */
public interface VOSucursal {

	/**
	 * @return El tamanho de la Sucursal
	 */
	public double getTamanho();
	
	/**
	 * @return el tipo de mercado de la Sucursal
	 */
	public String getTipoDeMercado();	
	
	/**
	 * @return las ventas locales de la Sucursal
	 */
	public double getVentasTotales();
	
	/**
	 * @return El id de la Sucursal
	 */
	public long getId();
	
	/**
	 * @return la ciudad de la Sucursal
	 */
	public long getIdCiudad();
	
	/**
	 * @return the direccion
	 */
	public String getDireccion();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la Sucursal
	 */
	public String toString();

}
